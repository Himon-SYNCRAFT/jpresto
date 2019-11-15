package pl.syncraft.presto.core.repository;

import pl.syncraft.presto.core.entity.Category;
import pl.syncraft.presto.core.entity.Image;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.filters.ProductFilter;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/30
 */
public interface ProductRepository extends CrudRepository<Product, Integer>, SearchableRepository<Product, ProductFilter> {
    void addCategory(Product product, Category category);
    void removeCategory(Product product, Category category);
    void addImage(Product product, Image image);
    void removeImage(Product product, Image image);
}
