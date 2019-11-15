package pl.syncraft.presto.core.usecase.AddImageToProduct;

import lombok.AllArgsConstructor;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/15
 */
@AllArgsConstructor
public class AddImageToProductRequest {
    public final Integer productId;
    public final String imageUrl;
}
