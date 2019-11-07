package pl.syncraft.presto.core.usecase;

import pl.syncraft.presto.core.PrestoError;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
@Getter
public abstract class Validator<R> {
    List<PrestoError> errors = new ArrayList<>();

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void addError(String message) {
        PrestoError error = new PrestoError(message);
        errors.add(error);
    }

    public void addError(PrestoError error) {
        errors.add(error);
    }

    public abstract boolean isValid(R request);
}
