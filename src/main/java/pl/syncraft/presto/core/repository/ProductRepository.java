package pl.syncraft.presto.core.repository;

import pl.syncraft.presto.core.ProductFilter;
import pl.syncraft.presto.core.entity.Product;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/30
 */
public interface ProductRepository extends CrudRepository<Product, ProductFilter> {
}
