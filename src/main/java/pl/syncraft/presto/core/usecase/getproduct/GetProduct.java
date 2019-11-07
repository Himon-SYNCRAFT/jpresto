package pl.syncraft.presto.core.usecase.getproduct;

import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.exception.NotFoundException;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.UseCase;
import lombok.AllArgsConstructor;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/30
 */
@AllArgsConstructor
public class GetProduct extends UseCase<GetProductRequest, Product> {
    private ProductRepository productRepository;

    public Product process(GetProductRequest request) {
        return productRepository.get(request.productId)
                .orElseThrow(() -> new NotFoundException(
                        "Product",
                        request.productId
                ));
    }
}
