package miu.waa.productapplication.controller;

import miu.waa.productapplication.model.Product;
import miu.waa.productapplication.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProductService productService;

    private Product product;
    private List<Product> productList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .title("product 1")
                .price(100)
                .quantity(2)
                .build();
        productList.add(product);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProduct() {
    }

    @Test
    void getAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(productList);
        // call API
        this.mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetProduct() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(product);

        // call API
        this.mockMvc.perform(get("/products/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct_OK() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(product);

        // call API
        this.mockMvc.perform(delete("/products/1"))
                .andDo(print())
                .andExpect(status().isOk());
        // then
        verify(productService).deleteProduct(anyLong());
    }

    @Test
    void deleteProduct_NotFound() throws Exception {
        // call API
        this.mockMvc.perform(delete("/products/1"))
                .andDo(print())
                .andExpect(status().isOk());
        // then
        verify(productService, never()).deleteProduct(anyLong());
    }
}