package pl.syncraft.presto.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import pl.syncraft.presto.core.ProductFilter;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.repository.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/06
 */
public class MyBatisProductRepository implements ProductRepository {
    @Override
    public Optional<Product> get(Integer id) {
        Product product = null;

        try (SqlSession session = MyBatisUtil.buildSessionFactory().openSession()) {
            product = session.selectOne("Product.getProduct", id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> find(ProductFilter filter) {
        System.out.println("filter: " + filter);
        try (SqlSession session = MyBatisUtil.buildSessionFactory().openSession()) {
            return session.selectList("findProducts", filter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product save(Product product) {
        try (SqlSession session = MyBatisUtil.buildSessionFactory().openSession()) {
            if (product.getId() == null) {
                session.insert("addProduct", product);
            } else {
                session.update("updateProduct", product);
            }

            session.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    @Override
    public void remove(Integer id) {
        try (SqlSession session = MyBatisUtil.buildSessionFactory().openSession()) {
            session.delete("deleteProduct", id);
            session.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
