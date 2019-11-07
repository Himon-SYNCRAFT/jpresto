package pl.syncraft.presto.core.exception;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/31
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String error) {
        super(error);
    }

    public NotFoundException(String type, int id) {
        super("Not found " + type + " with id " + id);
    }
}
