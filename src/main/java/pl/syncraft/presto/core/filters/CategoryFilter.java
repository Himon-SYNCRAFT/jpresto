package pl.syncraft.presto.core.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CategoryFilter {
    public Filter<Integer> id;
    public Filter<String> name;
}
