package pl.syncraft.presto.core.usecase.UpdateProduct;

import pl.syncraft.presto.core.usecase.Validator;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
public class UpdateProductValidator extends Validator<UpdateProductRequest> {
    @Override
    public boolean isValid(UpdateProductRequest request) {
        if (request.productId == null) {
            addError("productId is required and cannot be empty");
        }

        if (request.sku == null || request.sku.trim().isEmpty()) {
            addError("Sku is required and cannot be empty");
        }

        if (request.name == null || request.name.trim().isEmpty()) {
            addError("Name is required and cannot be empty");
        }

        return !hasErrors();
    }
}
