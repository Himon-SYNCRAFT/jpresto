package pl.syncraft.presto.repository.memory;

import pl.syncraft.presto.core.ProductFilter;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
public class InMemoryProductRepository implements ProductRepository {
    private static Map<Integer, Product> products = new HashMap<>();

    public InMemoryProductRepository() {
        products.put(
                1,
                Product.builder()
                        .id(1)
                        .name("kamera")
                        .sku("1")
                        .build()
        );

        products.put(
                2,
                Product.builder()
                        .id(2)
                        .name("rejestrator")
                        .sku("2")
                        .build()
        );

        products.put(
                3,
                Product.builder()
                        .id(3)
                        .name("zestaw")
                        .sku("3")
                        .build()
        );
    }

    @Override
    public Optional<Product> get(Integer id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> find(ProductFilter filter) {
        return null;
    }

    @Override
    public Product save(Product item) {
        Integer productId = item.getId();

        if (productId == null) {
            Optional<Integer> optionalId = products.keySet().stream().max((a, b) -> a - b);
            Integer nextId = optionalId.orElse(0);
            nextId++;
            item.setId(nextId);
        }

        products.put(item.getId(), item);
        return item;
    }

    @Override
    public void remove(Integer id) {
        products.remove(id);
    }
}
