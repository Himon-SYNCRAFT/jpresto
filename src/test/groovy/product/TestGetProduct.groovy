package product

import pl.syncraft.presto.core.entity.Product
import pl.syncraft.presto.core.repository.ProductRepository
import pl.syncraft.presto.core.usecase.GetProduct.GetProduct
import pl.syncraft.presto.core.usecase.GetProduct.GetProductRequest
import spock.lang.Specification

class TestGetProduct extends Specification {
    private ProductRepository productRepository = Mock()

    def "return product if provided proper input"() {
        given:
        def productId = 1
        Product product = Product.builder().id(productId).build()
        productRepository.get(productId) >> Optional.of(product)
        def action = new GetProduct(productRepository)

        when:
        def result = action.execute(new GetProductRequest(productId))
        println result.errors

        then:
        !result.hasErrors()
        result.data.id == productId
    }

    def "return response with error if product with provided id cannot be found"() {
        given:
        def productId = 1
        productRepository.get(productId) >> Optional.empty()
        def action = new GetProduct(productRepository)

        when:
        def result = action.execute(new GetProductRequest(productId))

        then:
        result.hasErrors()
    }
}
