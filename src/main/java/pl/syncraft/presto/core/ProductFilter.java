package pl.syncraft.presto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ProductFilter {
    public Integer id;
    public String name;
    public String sku;
}
