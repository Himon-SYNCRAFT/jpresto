package pl.syncraft.presto.core.usecase.addcategorytoproduct;

import lombok.AllArgsConstructor;
import pl.syncraft.presto.core.entity.Category;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.exception.NotFoundException;
import pl.syncraft.presto.core.repository.CategoryRepository;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.UseCase;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/07
 */
@AllArgsConstructor
public class AddCategoryToProduct extends UseCase<AddCategoryToProductRequest, Product> {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product process(AddCategoryToProductRequest request) {
        Product product = productRepository.get(request.productId)
                .orElseThrow(() -> new NotFoundException(
                        "Product",
                        request.productId
                )
        );

        Category category = categoryRepository.get(request.categoryId)
                .orElseThrow(() -> new NotFoundException(
                                "Category",
                                request.categoryId
                        )
                );

        product.addCategory(category);
        product = productRepository.save(product);

        return product;
    }
}
