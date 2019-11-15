package pl.syncraft.presto.core.usecase.RemoveImageFromProduct;

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
public class RemoveImageFromProduct extends UseCase<RemoveImageFromProductRequest, Product> {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Override
    public Product process(RemoveImageFromProductRequest request) {
        Product product = productRepository.get(request.productId)
                .orElseThrow(() -> new NotFoundException(
                                "Product",
                                request.productId
                        )
                );

        Image image = imageRepository.get(request.imageUrl)
                .orElseThrow(() -> new NotFoundException(
                        "Not found Image with url " + request.imageUrl
                ));

        if (!product.hasImage(image)) {
            return product;
        }

        productRepository.removeImage(product, image);
        product.removeImage(image);

        return product;
    }
}
