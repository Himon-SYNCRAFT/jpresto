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
    private Description description;
    @Builder.Default
    private List<Image> images = new ArrayList<>();

    public void addImage(@NonNull Image image) {
        images.add(image);
    }

    public void removeImage(@NonNull Image image) {
        images.remove(image);
    }

    public void addCategory(@NonNull Category category) {
        categories.add(category);
    }

    public void removeCategory(@NonNull Category category) {
        categories.remove(category);
    }
}
