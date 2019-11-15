package pl.syncraft.presto.core.repository;

import java.util.Optional;

public interface CrudRepository<T, PK> {
    Optional<T> get(PK primaryKey);
    T save(T item);
    void remove(Integer id);
}
