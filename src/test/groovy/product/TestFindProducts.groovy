package product

import pl.syncraft.presto.core.Filter
import pl.syncraft.presto.core.entity.Product
import pl.syncraft.presto.core.repository.ProductRepository
import pl.syncraft.presto.core.usecase.findproducts.FindProducts
import pl.syncraft.presto.core.usecase.findproducts.FindProductsRequest
import spock.lang.Specification

class TestFindProducts extends Specification {
    private ProductRepository productRepository = Mock()

    def "return list of products if provided proper input"() {
        given:
        def productName = "product"

        Product product = Product.builder().name(productName + "1").build()
        Product product2 = Product.builder().name(productName + "2").build()
        productRepository.find(_) >> Arrays.asList(product, product2)

        def action = new FindProducts(productRepository)

        when:
        def result = action.execute(new FindProductsRequest(
                null,
                null,
                new Filter<String>(productName, Filter.Operator.LIKE)
        ))

        then:
        !result.hasErrors()
        result.data.size() == 2
    }
}