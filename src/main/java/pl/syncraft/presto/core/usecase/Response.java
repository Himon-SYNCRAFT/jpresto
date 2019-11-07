package pl.syncraft.presto.core.usecase;

import pl.syncraft.presto.core.PrestoError;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
@Getter
@Setter
public class Response<E> {
    protected List<PrestoError> errors = new ArrayList<>();
    protected E data;

    public void addError(String message) {
        errors.add(new PrestoError(message));
    }

    public void addError(PrestoError error) {
        errors.add(error);
    }

    public void addAllErrors(List<PrestoError> errors) {
        if (errors != null)
            this.errors.addAll(errors);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
