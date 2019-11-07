package pl.syncraft.presto.core.repository;

import pl.syncraft.presto.core.CategoryFilter;
import pl.syncraft.presto.core.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, CategoryFilter> {
}
