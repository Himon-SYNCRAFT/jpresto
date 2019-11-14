package pl.syncraft.presto.web;

import pl.syncraft.presto.TransactionManager;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.ChainUseCase.GetAndRemoveProduct;
import pl.syncraft.presto.core.usecase.UseCase;
import pl.syncraft.presto.core.usecase.addnewproduct.AddNewProduct;
import pl.syncraft.presto.core.usecase.findproducts.FindProducts;
import pl.syncraft.presto.core.usecase.getproduct.GetProduct;
import pl.syncraft.presto.core.usecase.removeproduct.RemoveProduct;
import pl.syncraft.presto.core.usecase.updateproduct.UpdateProduct;
import pl.syncraft.presto.repository.jooq.JooqProductRepository;
import pl.syncraft.presto.repository.jooq.JooqTransactionManager;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
public class Context {
    private static Context instance = null;
//    public static ProductRepository productRepository = new InMemoryProductRepository();
//    public static ProductRepository productRepository = new MyBatisProductRepository();
    public static ProductRepository productRepository = new JooqProductRepository();

    private Context() {

    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }

    public static TransactionManager getTransactionManager() {
//        return new MyBatisTransactionManager();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/jpresto",
                    "presto",
                    "miton5678"
            );
            return new JooqTransactionManager(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <I extends UseCase> I getUseCase(final Class<I> clazz) {
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
        } else if (clazz == GetAndRemoveProduct.class) {
            return (I) new GetAndRemoveProduct();
        }
        throw new RuntimeException("Cannot find UseCase for provided input");
    };
}
