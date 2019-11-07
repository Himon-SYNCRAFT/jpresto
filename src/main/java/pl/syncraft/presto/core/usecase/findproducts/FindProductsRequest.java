package pl.syncraft.presto.core.usecase.findproducts;

import lombok.AllArgsConstructor;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/06
 */
@AllArgsConstructor
public class FindProductsRequest {
    public final Integer productId;
    public final String sku;
    public final String name;
}
