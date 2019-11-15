package pl.syncraft.presto.web;

import pl.syncraft.presto.TransactionManager;
import pl.syncraft.presto.core.repository.CategoryRepository;
import pl.syncraft.presto.core.repository.ImageRepository;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.AddCategoryToProduct.AddCategoryToProduct;
import pl.syncraft.presto.core.usecase.AddImageToProduct.AddImageToProduct;
import pl.syncraft.presto.core.usecase.AddNewProduct.AddNewProduct;
import pl.syncraft.presto.core.usecase.FindProducts.FindProducts;
import pl.syncraft.presto.core.usecase.GetProduct.GetProduct;
import pl.syncraft.presto.core.usecase.RemoveCategoryFromProduct.RemoveCategoryFromProduct;
import pl.syncraft.presto.core.usecase.RemoveImageFromProduct.RemoveImageFromProduct;
import pl.syncraft.presto.core.usecase.RemoveProduct.RemoveProduct;
import pl.syncraft.presto.core.usecase.UpdateProduct.UpdateProduct;
import pl.syncraft.presto.core.usecase.UseCase;
import pl.syncraft.presto.repository.jooq.JooqCategoryRepository;
import pl.syncraft.presto.repository.jooq.JooqImageRepository;
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
    public static CategoryRepository categoryRepository = new JooqCategoryRepository();
    public static ImageRepository imageRepository = new JooqImageRepository();

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
        } else if (clazz == AddCategoryToProduct.class) {
            return (I) new AddCategoryToProduct(productRepository, categoryRepository);
        } else if (clazz == RemoveCategoryFromProduct.class) {
            return (I) new RemoveCategoryFromProduct(productRepository, categoryRepository);
        } else if (clazz == AddImageToProduct.class) {
            return (I) new AddImageToProduct(productRepository, imageRepository);
        } else if (clazz == RemoveImageFromProduct.class) {
            return (I) new RemoveImageFromProduct(productRepository, imageRepository);
        }

        throw new RuntimeException("Cannot find UseCase for provided input");
    };
}
