package pl.syncraft.presto.core.usecase.UpdateProduct;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */

@Data
@AllArgsConstructor
public class UpdateProductRequest {
    final public Integer productId;
    final public String name;
    final public String sku;
}
