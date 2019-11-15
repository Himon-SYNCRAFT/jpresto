package pl.syncraft.presto.core.usecase.RemoveCategoryFromProduct;

import lombok.AllArgsConstructor;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/15
 */
@AllArgsConstructor
public class RemoveCategoryFromProductRequest {
    final public Integer productId;
    final public Integer categoryId;
}
