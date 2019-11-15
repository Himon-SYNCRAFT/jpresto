package pl.syncraft.presto.core.usecase.RemoveProduct;

import lombok.AllArgsConstructor;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.UseCase;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/07
 */
@AllArgsConstructor
public class RemoveProduct extends UseCase<RemoveProductRequest, Integer> {
    private final ProductRepository productRepository;

    @Override
    public Integer process(RemoveProductRequest request) {
        productRepository.remove(request.productId);
        return request.productId;
    }
}
