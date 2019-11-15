package pl.syncraft.presto.core.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Zawlocki
 * @date 2019/10/30
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String sku;

    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    @Builder.Default
    private List<DescriptionItem> description = new ArrayList<>();

    @Builder.Default
    private List<Image> images = new ArrayList<>();

    public void addImage(@NonNull Image image) {
        if (!hasImage(image))
            images.add(image);
    }

    public void removeImage(@NonNull Image image) {
        if (hasImage(image))
            images.remove(image);
    }

    public boolean hasImage(@NonNull Image image) {
        return images.contains(image);
    }

    public void addCategory(@NonNull Category category) {
        if (!hasCategory(category))
            categories.add(category);
    }

    public void removeCategory(@NonNull Category category) {
        if (hasCategory(category))
            categories.remove(category);
    }

    public boolean hasCategory(@NonNull Category category) {
        return categories.contains(category);
    }

    public void addDescription(@NonNull DescriptionItem descriptionItem) {
        if (!hasDescription(descriptionItem))
            description.add(descriptionItem);
    }

    public void removeDescription(@NonNull DescriptionItem descriptionItem) {
        if (hasDescription(descriptionItem))
            description.remove(descriptionItem);
    }

    public boolean hasDescription(@NonNull DescriptionItem description) {
        return this.description.contains(description);
    }
}
