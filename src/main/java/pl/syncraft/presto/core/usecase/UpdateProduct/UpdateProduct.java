package pl.syncraft.presto.core.usecase.UpdateProduct;

import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.exception.NotFoundException;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.UseCase;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
public class UpdateProduct extends UseCase<UpdateProductRequest, Product> {
    private ProductRepository productRepository;

    public UpdateProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
        addValidator(new UpdateProductValidator());
    }

    @Override
    public Product process(UpdateProductRequest request) {
        Product product = productRepository.get(request.productId)
                .orElseThrow(() -> new NotFoundException(
                        "Product",
                        request.productId
                ));

        product.setName(request.name);
        product.setSku(request.sku);

        product = productRepository.save(product);

        return product;
    }
}
