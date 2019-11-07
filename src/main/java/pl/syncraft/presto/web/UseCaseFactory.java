package pl.syncraft.presto.web;

import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.UseCase;
import pl.syncraft.presto.core.usecase.addnewproduct.AddNewProduct;
import pl.syncraft.presto.core.usecase.findproducts.FindProducts;
import pl.syncraft.presto.core.usecase.getproduct.GetProduct;
import pl.syncraft.presto.core.usecase.removeproduct.RemoveProduct;
import pl.syncraft.presto.core.usecase.updateproduct.UpdateProduct;
import pl.syncraft.presto.repository.mybatis.MyBatisProductRepository;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
public class UseCaseFactory {
    private static UseCaseFactory instance = null;
//    public static ProductRepository productRepository = new InMemoryProductRepository();
    public static ProductRepository productRepository = new MyBatisProductRepository();

    private UseCaseFactory() {

    }

    public static UseCaseFactory getInstance() {
        if (instance == null) {
            instance = new UseCaseFactory();
        }

        return instance;
    }

    <I extends UseCase> I getUseCase(final Class<I> clazz) {
        if (clazz == GetProduct.class) {
            return (I) new GetProduct(productRepository);
        } else if (clazz == AddNewProduct.class) {
            return (I) new AddNewProduct(productRepository);
        } else if (clazz == UpdateProduct.class) {
            return (I) new UpdateProduct(productRepository);
        } else if (clazz == FindProducts.class) {
            return (I) new FindProducts(productRepository);
        } else if (clazz == RemoveProduct.class) {
            return (I) new RemoveProduct(productRepository);
        }
        throw new RuntimeException("Cannot find UseCase for provided input");
    };
}
