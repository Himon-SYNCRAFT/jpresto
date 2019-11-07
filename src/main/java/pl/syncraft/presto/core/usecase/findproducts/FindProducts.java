package pl.syncraft.presto.core.usecase.findproducts;

import pl.syncraft.presto.core.ProductFilter;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.UseCase;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/06
 */
@AllArgsConstructor
public class FindProducts extends UseCase<FindProductsRequest, List<Product>> {
    private ProductRepository productRepository;

    @Override
    public List<Product> process(FindProductsRequest request) {
        ProductFilter filter = new ProductFilter(
                request.productId,
                request.name,
                request.sku
        );

        return productRepository.find(filter);
    }
}
