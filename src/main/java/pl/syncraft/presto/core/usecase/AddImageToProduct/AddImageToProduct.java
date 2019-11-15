package pl.syncraft.presto.core.usecase.AddImageToProduct;

import lombok.AllArgsConstructor;
import pl.syncraft.presto.core.entity.Image;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.exception.NotFoundException;
import pl.syncraft.presto.core.repository.ImageRepository;
import pl.syncraft.presto.core.repository.ProductRepository;
import pl.syncraft.presto.core.usecase.UseCase;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/15
 */
@AllArgsConstructor
public class AddImageToProduct extends UseCase<AddImageToProductRequest, Product> {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Override
    public Product process(AddImageToProductRequest request) {
        Product product = productRepository.get(request.productId)
                .orElseThrow(() -> new NotFoundException(
                            "Not found Product with id " + request.productId
                        )
                );

        Image image = imageRepository.get(request.imageUrl)
                .orElseThrow(() -> new NotFoundException(
                        "Not found Image with url " + request.imageUrl
                ));

        if (product.hasImage(image)) {
            return product;
        }

        productRepository.addImage(product, image);
        product.addImage(image);

        return product;
    }
}
