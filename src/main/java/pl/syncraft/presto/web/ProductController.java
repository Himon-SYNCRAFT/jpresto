package pl.syncraft.presto.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.syncraft.presto.core.filters.Filter;
import pl.syncraft.presto.core.entity.Product;
import pl.syncraft.presto.core.usecase.AddCategoryToProduct.AddCategoryToProduct;
import pl.syncraft.presto.core.usecase.AddCategoryToProduct.AddCategoryToProductRequest;
import pl.syncraft.presto.core.usecase.AddImageToProduct.AddImageToProduct;
import pl.syncraft.presto.core.usecase.AddImageToProduct.AddImageToProductRequest;
import pl.syncraft.presto.core.usecase.AddNewProduct.AddNewProduct;
import pl.syncraft.presto.core.usecase.AddNewProduct.AddNewProductRequest;
import pl.syncraft.presto.core.usecase.FindProducts.FindProducts;
import pl.syncraft.presto.core.usecase.FindProducts.FindProductsRequest;
import pl.syncraft.presto.core.usecase.GetProduct.GetProduct;
import pl.syncraft.presto.core.usecase.GetProduct.GetProductRequest;
import pl.syncraft.presto.core.usecase.RemoveCategoryFromProduct.RemoveCategoryFromProduct;
import pl.syncraft.presto.core.usecase.RemoveCategoryFromProduct.RemoveCategoryFromProductRequest;
import pl.syncraft.presto.core.usecase.RemoveImageFromProduct.RemoveImageFromProduct;
import pl.syncraft.presto.core.usecase.RemoveImageFromProduct.RemoveImageFromProductRequest;
import pl.syncraft.presto.core.usecase.RemoveProduct.RemoveProduct;
import pl.syncraft.presto.core.usecase.RemoveProduct.RemoveProductRequest;
import pl.syncraft.presto.core.usecase.Response;
import pl.syncraft.presto.core.usecase.UpdateProduct.UpdateProduct;
import pl.syncraft.presto.core.usecase.UpdateProduct.UpdateProductRequest;
import pl.syncraft.presto.web.dto.CategoryId;
import pl.syncraft.presto.web.dto.ImageUrl;
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
    Context useCaseFactory = Context.getInstance();

    private class RequestCategory {
        public Integer categoryId;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getProduct(@PathVariable Integer id) {
        GetProduct getProduct = Context.getUseCase(GetProduct.class);
        Response<Product> response = getProduct.execute(new GetProductRequest(id));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody ProductDto request) {
        AddNewProduct addNewProduct = Context.getUseCase(AddNewProduct.class);

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
        UpdateProduct updateProduct = Context.getUseCase(UpdateProduct.class);

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
        FindProducts findProducts = Context.getUseCase(FindProducts.class);

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
        RemoveProduct removeProduct = Context.getUseCase(RemoveProduct.class);
        Response<Integer> response = removeProduct.execute(new RemoveProductRequest(id));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/categories")
    public ResponseEntity addCategoryToProduct(@PathVariable Integer id, @RequestBody CategoryId input) {
        AddCategoryToProduct addCategoryToProduct = Context.getUseCase(AddCategoryToProduct.class);
        Response<Product> response = addCategoryToProduct.execute(
                new AddCategoryToProductRequest(id, input.getCategoryId()));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/categories")
    public ResponseEntity removeCategoryFromProduct(@PathVariable Integer id, @RequestBody CategoryId input) {
        RemoveCategoryFromProduct removeCategoryFromProduct = Context
                .getUseCase(RemoveCategoryFromProduct.class);
        Response<Product> response = removeCategoryFromProduct.execute(
                new RemoveCategoryFromProductRequest(id, input.getCategoryId()));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/images")
    public ResponseEntity addImageToProduct(@PathVariable Integer id, @RequestBody ImageUrl input) {
        AddImageToProduct useCase = Context.getUseCase(AddImageToProduct.class);
        Response<Product> response = useCase.execute(
                new AddImageToProductRequest(id, input.getImageUrl()));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/images")
    public ResponseEntity removeImageFromProduct(@PathVariable Integer id, @RequestBody ImageUrl input) {
        RemoveImageFromProduct useCase = Context
                .getUseCase(RemoveImageFromProduct.class);
        Response<Product> response = useCase.execute(
                new RemoveImageFromProductRequest(id, input.getImageUrl()));

        if (response.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
