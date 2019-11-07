package product

import pl.syncraft.presto.core.entity.Product
import pl.syncraft.presto.core.repository.ProductRepository
import pl.syncraft.presto.core.usecase.addnewproduct.AddNewProduct
import pl.syncraft.presto.core.usecase.addnewproduct.AddNewProductRequest
import spock.lang.Specification

class TestAddNewProduct extends Specification {
    private ProductRepository productRepository = Mock()

    def "return product if action save product successfully"() {
        given:
        def productName = 'name'
        def productSku = 'sku'
        Product product = Product.builder()
                .id(1)
                .name(productName)
                .sku(productSku)
                .build()
        def action = new AddNewProduct(productRepository)
        productRepository.save(_) >> product

        when:
        def result = action.execute(new AddNewProductRequest(productName, productSku))

        then:
        result.data.id != null
        result.data.name == productName
        result.data.sku == productSku
    }

    def "return response with error if request is invalid"() {
        given:
        def productName = null
        def productSku = ''

        def action = new AddNewProduct(productRepository)

        when:
        def result = action.execute(new AddNewProductRequest(productName, productSku))

        then:
        result.hasErrors()
    }
}
