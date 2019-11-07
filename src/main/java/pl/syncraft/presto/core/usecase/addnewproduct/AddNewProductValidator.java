package pl.syncraft.presto.core.usecase.addnewproduct;

import pl.syncraft.presto.core.usecase.Validator;
import lombok.NonNull;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/31
 */
public class AddNewProductValidator extends Validator<AddNewProductRequest> {
    @Override
    public boolean isValid(@NonNull AddNewProductRequest request) {
        if (request.sku == null || request.sku.trim().isEmpty()) {
            addError("Sku is required and cannot be empty");
        }

        if (request.name == null || request.name.trim().isEmpty()) {
            addError("Name is required and cannot be empty");
        }

        return !hasErrors();
    }
}
