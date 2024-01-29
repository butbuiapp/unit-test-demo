package miu.waa.productapplication.repository;

import miu.waa.productapplication.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void testExistsByTitle() {
        String title = "test";
        // given
        Product product = Product.builder()
                .title(title)
                .price(100)
                .quantity(10)
                .build();
        productRepository.save(product);

        // when
        boolean result = productRepository.existsByTitle(title);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void testGetAllByTitle_Found() {
        String title = "test";
        // given
        Product product = Product.builder()
                .title(title)
                .price(100)
                .quantity(10)
                .build();
        productRepository.save(product);

        product = Product.builder()
                .title(title)
                .price(102)
                .quantity(2)
                .build();
        productRepository.save(product);

        List<Product> allProducts = productRepository.findAllByTitle(title);
        assertThat(allProducts.get(0).getTitle()).isEqualTo(title);
        assertThat(allProducts.size()).isEqualTo(2);
    }

    @Test
    void testGetAllByTitle_NotFound() {
        List<Product> allProducts = productRepository.findAllByTitle("");
        assertThat(allProducts.isEmpty()).isTrue();
    }
}