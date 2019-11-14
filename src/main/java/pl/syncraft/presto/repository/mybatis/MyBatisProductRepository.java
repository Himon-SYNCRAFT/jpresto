package pl.syncraft.presto.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import pl.syncraft.presto.core.ProductFilter;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.web.Context;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/06
 */
public class MyBatisProductRepository implements ProductRepository {
    @Override
    public Optional<Product> get(Integer id) {
        SqlSession session = (SqlSession) Context.getTransactionManager().getSession();
        Product product = session.selectOne("Product.getProduct", id);
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> find(ProductFilter filter) {
        SqlSession session = (SqlSession) Context.getTransactionManager().getSession();
        return session.selectList("findProducts", filter);
    }

    @Override
    public Product save(Product product) {
        SqlSession session = (SqlSession) Context.getTransactionManager().getSession();

        if (product.getId() == null) {
            session.insert("addProduct", product);
        } else {
            session.update("updateProduct", product);
        }

        return product;
    }

    @Override
    public void remove(Integer id) {
        SqlSession session = (SqlSession) Context.getTransactionManager().getSession();
        session.delete("deleteProduct", id);
    }
}
