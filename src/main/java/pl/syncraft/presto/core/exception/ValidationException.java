package pl.syncraft.presto.core.exception;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/31
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String error) {
        super(error);
    }
}
