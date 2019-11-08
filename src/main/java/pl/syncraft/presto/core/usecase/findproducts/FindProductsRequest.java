package pl.syncraft.presto.core.usecase.findproducts;

import lombok.AllArgsConstructor;
import pl.syncraft.presto.core.Filter;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/06
 */
@AllArgsConstructor
public class FindProductsRequest {
    public final Filter<Integer> productId;
    public final Filter<String> sku;
    public final Filter<String> name;
}
