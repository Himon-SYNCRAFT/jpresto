package pl.syncraft.presto.core.repository;

import pl.syncraft.presto.core.entity.Category;
import pl.syncraft.presto.core.filters.CategoryFilter;

public interface CategoryRepository extends CrudRepository<Category, Integer>, SearchableRepository<Category, CategoryFilter> {
}
