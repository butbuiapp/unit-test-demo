package miu.waa.productapplication.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.productapplication.dto.ResponseData;
import miu.waa.productapplication.model.Product;
import miu.waa.productapplication.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseData addProduct(@RequestBody Product product) {
        Product p = productService.saveProduct(product);
        ResponseData res = new ResponseData();
        res.setSuccess(true);
        res.setData(p);
        return res;
    }

    @GetMapping
    public ResponseData getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ResponseData res = new ResponseData();
        res.setSuccess(true);
        res.setData(products);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseData updateProduct(@PathVariable long id, @RequestBody Product product) {
        ResponseData res = new ResponseData();
        Product p = productService.updateProduct(id, product);
        if (p == null) {
            res.setSuccess(false);
            res.setMessage("Product not found id " + id);
            return res;
        }
        res.setSuccess(true);
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseData deleteProduct(@PathVariable long id) {
        ResponseData res = new ResponseData();

        Product p = productService.getProductById(id);
        if (p == null) {
            res.setSuccess(false);
            res.setMessage("Product not found id " + id);
            return res;
        }
        productService.deleteProduct(id);
        res.setSuccess(true);
        return res;
    }

}
