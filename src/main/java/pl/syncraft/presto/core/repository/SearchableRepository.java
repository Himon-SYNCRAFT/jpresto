package pl.syncraft.presto.core.repository;

import java.util.List;

public interface SearchableRepository<T, F> {
    List<T> find(F filter);
}
