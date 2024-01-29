package miu.waa.productapplication.service;

import miu.waa.productapplication.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    Product updateProduct(long id, Product product);
    List<Product> getAllProducts();
    Product getProductById(long id);
    void deleteProduct(long id);
}
