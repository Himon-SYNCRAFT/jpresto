package pl.syncraft.presto.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/30
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private Category parent;

    public String getPath(String separator) {
        String path = name;

        if (parent != null) {
            path = parent.getPath(separator) + separator + name;
        }

        return path;
    }
}
