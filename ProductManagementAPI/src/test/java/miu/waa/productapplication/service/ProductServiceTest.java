package miu.waa.productapplication.service;

import miu.waa.productapplication.model.Product;
import miu.waa.productapplication.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void canGetAllProducts() {
        // when
        productService.getAllProducts();
        // then
        verify(productRepository).findAll();

    }

    @Test
    void saveProduct() {
        // given
        Product product = Product.builder()
                .title("product 1")
                .price(100)
                .quantity(2)
                .build();
        // when
        productService.saveProduct(product);

        // then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(product).isEqualTo(capturedProduct);
    }

    @Test
    void saveProduct_OK() {
        // given
        Product product = Product.builder()
//                .id(1)
                .title("product 1")
                .price(100)
                .quantity(2)
                .build();
        // when
        when(productRepository.save(product)).thenReturn(product); // mock return product when call save

        // then
        assertThat(productService.saveProduct(product)).isEqualTo(product);
    }

    @Test
    void willThrowExceptionWhenProductDoesNotExist() {
        // given
        Product product = Product.builder()
                .id(10)
                .title("product 1")
                .price(100)
                .quantity(2)
                .build();

        // then
        assertThatThrownBy(() -> productService.updateProduct(10, product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Product " + product.getId() + " does not exist");

        // save is never called
        verify(productRepository, never()).save(any());
    }

    @Test
    void canUpdateProduct() {
        // given
        Product inputProduct = Product.builder()
                .id(10)
                .title("product 11")
                .price(200)
                .quantity(3)
                .build();

        Product existProduct = Product.builder()
                .id(10)
                .title("product 1")
                .price(100)
                .quantity(2)
                .build();
        given(productRepository.findById(anyLong())).willReturn(Optional.ofNullable(existProduct));

        // when
        productService.updateProduct(10, inputProduct);

        // then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(inputProduct).isEqualTo(capturedProduct);
    }

    @Test
    void getProductById() {
        // given
        long productId = 1L;
        Product mockProduct = Product.builder()
                .id(productId)
                .title("product 1")
                .price(100)
                .quantity(2)
                .build();

        // both ways are ok
        //when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockProduct));
        given(productRepository.findById(anyLong())).willReturn(Optional.of(mockProduct));

        // Act
        Product result = productService.getProductById(productId);

        // Assert
        assertThat(result).isEqualTo(mockProduct);
    }

    @Test
    void deleteProduct() {
        // when
        productService.deleteProduct(anyLong());
        // then
        verify(productRepository).deleteById(anyLong());
    }
}