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
public class Image {
    private String url;
}
