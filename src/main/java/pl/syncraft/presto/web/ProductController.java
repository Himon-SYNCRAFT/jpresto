package pl.syncraft.presto.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.syncraft.presto.core.Filter;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.usecase.Response;
import pl.syncraft.presto.core.usecase.addnewproduct.AddNewProduct;
import pl.syncraft.presto.core.usecase.addnewproduct.AddNewProductRequest;
import pl.syncraft.presto.core.usecase.findproducts.FindProducts;
import pl.syncraft.presto.core.usecase.findproducts.FindProductsRequest;
import pl.syncraft.presto.core.usecase.getproduct.GetProduct;
import pl.syncraft.presto.core.usecase.getproduct.GetProductRequest;
import pl.syncraft.presto.core.usecase.removeproduct.RemoveProduct;
import pl.syncraft.presto.core.usecase.removeproduct.RemoveProductRequest;
import pl.syncraft.presto.core.usecase.updateproduct.UpdateProduct;
import pl.syncraft.presto.core.usecase.updateproduct.UpdateProductRequest;
import pl.syncraft.presto.web.dto.ProductDto;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/04
 */
@RestController
@RequestMapping(path = "/product")
public class ProductController {
    UseCaseFactory useCaseFactory = UseCaseFactory.getInstance();

    @GetMapping(value = "/{id}")
    public ResponseEntity getProduct(@PathVariable Integer id) {
        GetProduct getProduct = useCaseFactory.getUseCase(GetProduct.class);
        Response<Product> response = getProduct.execute(new GetProductRequest(id));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody ProductDto request) {
        AddNewProduct addNewProduct = useCaseFactory.getUseCase(AddNewProduct.class);

        Response<Product> response = addNewProduct.execute(new AddNewProductRequest(
                request.getName(),
                request.getSku()
        ));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody ProductDto request) {
        UpdateProduct updateProduct = useCaseFactory.getUseCase(UpdateProduct.class);

        Response<Product> response = updateProduct.execute(new UpdateProductRequest(
                id,
                request.getName(),
                request.getSku()
        ));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity findProducts(@RequestParam(value = "search", required = false) String search) {
        FindProducts findProducts = useCaseFactory.getUseCase(FindProducts.class);

        Pattern pattern = Pattern.compile("(\\w+?)(!:|>:|<:|>|<|:)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        Filter<String> filterName = new Filter<>();
        Filter<String> filterSku = new Filter<>();
        Filter<Integer> filterProductId = new Filter<>();

        while (matcher.find()) {
            String key = matcher.group(1);
            String operator = matcher.group(2);
            String value = matcher.group(3);

            switch (key) {
                case "name":
                    filterName.setOperator(operator);

                    if (operator.equals(":")) {
                        filterName.setOperator(Filter.Operator.LIKE);
                    } else if (operator.equals("!:")) {
                        filterName.setOperator(Filter.Operator.NOT_LIKE);
                    }

                    filterName.setValue('%' + value + '%');
                    break;

                case "sku":
                    filterSku.setOperator(operator);

                    if (operator.equals(":")) {
                        filterSku.setOperator(Filter.Operator.LIKE);
                    } else if (operator.equals("!:")) {
                        filterSku.setOperator(Filter.Operator.NOT_LIKE);
                    }

                    filterSku.setValue('%' + value + '%');
                    break;

                case "id":
                    filterProductId.setOperator(operator);
                    filterProductId.setValue(Integer.valueOf(value));
                    break;
            }
        }

        Response<List<Product>> response = findProducts.execute(new FindProductsRequest(
                filterProductId,
                filterSku,
                filterName
        ));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeProduct(@PathVariable Integer id) {
        RemoveProduct removeProduct = useCaseFactory.getUseCase(RemoveProduct.class);
        Response<Integer> response = removeProduct.execute(new RemoveProductRequest(id));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
