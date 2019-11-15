package pl.syncraft.presto.core.usecase.RemoveCategoryFromProduct;

import lombok.AllArgsConstructor;
import pl.syncraft.presto.core.entity.Category;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.exception.NotFoundException;
import pl.syncraft.presto.core.repository.CategoryRepository;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.UseCase;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/15
 */
@AllArgsConstructor
public class RemoveCategoryFromProduct extends UseCase<RemoveCategoryFromProductRequest, Product> {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product process(RemoveCategoryFromProductRequest request) {
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

        if (!product.hasCategory(category)) {
            return product;
        }

        productRepository.removeCategory(product, category);
        product.removeCategory(category);

        return product;
    }
}
