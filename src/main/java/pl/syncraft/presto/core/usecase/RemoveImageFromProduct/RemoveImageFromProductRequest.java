package pl.syncraft.presto.core.usecase.RemoveImageFromProduct;

import lombok.AllArgsConstructor;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/15
 */
@AllArgsConstructor
public class RemoveImageFromProductRequest {
    public final Integer productId;
    public final String imageUrl;
}
