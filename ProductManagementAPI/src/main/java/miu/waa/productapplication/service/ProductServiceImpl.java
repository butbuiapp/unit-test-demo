package miu.waa.productapplication.service;

import lombok.RequiredArgsConstructor;
import miu.waa.productapplication.model.Product;
import miu.waa.productapplication.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setTitle(product.getTitle());
            p.setPrice(product.getPrice());
            p.setQuantity(product.getQuantity());
        } else {
            throw new IllegalArgumentException("Product " + product.getId() + " does not exist");
        }
        return productRepository.save(p);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
