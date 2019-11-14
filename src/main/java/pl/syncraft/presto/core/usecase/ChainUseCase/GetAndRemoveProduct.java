package pl.syncraft.presto.core.usecase.ChainUseCase;

import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.usecase.UseCase;
import pl.syncraft.presto.core.usecase.getproduct.GetProduct;
import pl.syncraft.presto.core.usecase.getproduct.GetProductRequest;
import pl.syncraft.presto.core.usecase.removeproduct.RemoveProduct;
import pl.syncraft.presto.core.usecase.removeproduct.RemoveProductRequest;
import pl.syncraft.presto.web.Context;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/12
 */
public class GetAndRemoveProduct extends UseCase<GetProductRequest, Product> {
    @Override
    public Product process(GetProductRequest request) {
        GetProduct getProduct = Context.getUseCase(GetProduct.class);
        Product product = getProduct.process(request);

        RemoveProduct removeProduct = Context.getUseCase(RemoveProduct.class);
        removeProduct.process(new RemoveProductRequest(request.productId));

        return product;
    }
}
