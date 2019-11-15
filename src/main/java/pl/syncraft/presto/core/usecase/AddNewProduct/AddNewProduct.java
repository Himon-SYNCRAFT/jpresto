package pl.syncraft.presto.core.usecase.AddNewProduct;

import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.UseCase;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/31
 */
public class AddNewProduct extends UseCase<AddNewProductRequest, Product> {
    private ProductRepository productRepository;

    public AddNewProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
        addValidator(new AddNewProductValidator());
    }

    @Override
    public Product process(AddNewProductRequest request) {
        Product product = Product.builder()
                .name(request.name)
                .sku(request.sku)
                .build();

        product = productRepository.save(product);
        return product;
    }
}
