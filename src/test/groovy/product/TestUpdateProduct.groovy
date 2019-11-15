package product

import pl.syncraft.presto.core.entity.Product
import pl.syncraft.presto.core.repository.ProductRepository
import pl.syncraft.presto.core.usecase.UpdateProduct.UpdateProduct
import pl.syncraft.presto.core.usecase.UpdateProduct.UpdateProductRequest
import spock.lang.Specification

class TestUpdateProduct extends Specification {
    private ProductRepository productRepository = Mock()

    def "return product if action save product successfully"() {
        given:
        def productId = 1
        def productName = 'name'
        def productSku = 'sku'

        Product product = Product.builder()
                .id(productId)
                .name('oldName')
                .sku('oldSku')
                .build()

        Product newProduct = Product.builder()
            .id(productId)
            .name(productName)
            .sku(productSku)
            .build()

        def action = new UpdateProduct(productRepository)
        productRepository.get(productId) >> Optional.of(product)
        productRepository.save(product) >> newProduct

        when:
        def result = action.execute(new UpdateProductRequest(
                productId, productName, productSku))

        then:
        result.data.id == productId
        result.data.name == productName
        result.data.sku == productSku
    }

    def "return response with error if request is invalid"() {
        given:
        def productId = 1
        def productName = null
        def productSku = ''

        Product product = Product.builder()
                .id(productId)
                .name(productName)
                .sku(productSku)
                .build()

        def action = new UpdateProduct(productRepository)
        productRepository.get(productId) >> product

        when:
        def result = action.execute(new UpdateProductRequest(
                productId, productName, productSku))

        then:
        result.hasErrors()
    }

    def "return response with error if productId was not provided"() {
        given:
        def productId = null
        def productName = 'name'
        def productSku = 'sku'

        def action = new UpdateProduct(productRepository)

        when:
        def result = action.execute(new UpdateProductRequest(
                productId, productName, productSku))

        then:
        result.hasErrors()
    }
}
