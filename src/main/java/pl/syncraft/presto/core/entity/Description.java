package pl.syncraft.presto.core.entity;

import lombok.*;

import java.util.List;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/30
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Description {
    private List<DescriptionItem> descriptionItems;

    public void addDescriptionItem(@NonNull DescriptionItem item) {
        descriptionItems.add(item);
    }
}
