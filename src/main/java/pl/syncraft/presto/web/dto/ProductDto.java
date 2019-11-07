package pl.syncraft.presto.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private String name;
    private String sku;
}
