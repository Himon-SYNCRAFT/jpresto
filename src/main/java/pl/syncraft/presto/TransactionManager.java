package pl.syncraft.presto;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/12
 */
public interface TransactionManager<T> extends AutoCloseable {
    T getSession();
}
