package pl.syncraft.presto.repository.mybatis.mapper;

import pl.syncraft.presto.core.ProductFilter;
import pl.syncraft.presto.core.entity.Product;

import java.util.List;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/08
 */
public interface ProductMapper {
    Product getProduct(Integer productId);
    List<Product> findProducts(ProductFilter filter);
    Integer addProduct(Product product);
    Integer updateProduct(Product product);
    Integer deleteProduct(Integer productId);
}
