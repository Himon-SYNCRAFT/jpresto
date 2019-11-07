package pl.syncraft.presto.core.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, F> {
    Optional<T> get(Integer id);
    List<T> find(F filter);
    T save(T item);
    void remove(Integer id);
}
