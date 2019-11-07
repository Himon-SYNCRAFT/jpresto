package pl.syncraft.presto.core.usecase;

import pl.syncraft.presto.core.PrestoError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
public abstract class UseCase<Request, E> {
    private List<Validator<Request>> validators = new ArrayList<>();

    public void addValidator(Validator<Request> validator) {
        validators.add(validator);
    }

    public abstract E process(Request request);

    public Response<E> execute(Request request) {
        Response<E> response = new Response<>();
        validate(request, response);

        if (response.hasErrors()) {
            return response;
        }

        try {
            E entity = process(request);
            response.setData(entity);
        } catch (Exception e) {
            response.addError(e.getMessage());
        }

        return response;
    }

    private void validate(Request request, Response response) {
        for (Validator<Request> validator : validators) {
            if (!validator.isValid(request)) {
                for (PrestoError s : validator.getErrors())
                    System.out.println("error: " + s.getMessage());
                response.addAllErrors(validator.getErrors());
            }
        }
    }
}
