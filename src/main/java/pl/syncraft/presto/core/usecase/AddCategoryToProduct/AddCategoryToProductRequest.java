package pl.syncraft.presto.core.usecase.AddCategoryToProduct;

import lombok.AllArgsConstructor;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/07
 */
@AllArgsConstructor
public class AddCategoryToProductRequest {
    final public Integer productId;
    final public Integer categoryId;
}
