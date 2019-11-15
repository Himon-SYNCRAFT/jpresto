package pl.syncraft.presto.core.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ImageFilter {
    public Filter<String> url;
}
