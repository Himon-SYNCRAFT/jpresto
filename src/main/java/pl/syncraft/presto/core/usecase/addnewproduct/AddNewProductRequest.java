package pl.syncraft.presto.core.usecase.addnewproduct;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/31
 */
@Data
@AllArgsConstructor
public class AddNewProductRequest {
    final public String name;
    final public String sku;
}
