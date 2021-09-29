package com.rei.interview.product.controller;

import com.rei.interview.product.model.Product;
import com.rei.interview.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductWebService {

    private final ProductService productService;

    public ProductWebService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findProduct(@PathVariable("id") String id) {
        Optional<Product> product = productService.getProduct(id);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(product);
    }

    @GetMapping({"/brand/{brand}"})
    public  ResponseEntity<Object> findProductsByBrand(@PathVariable("brand") String brand) {
        List<Product> productEntities = productService.getAllProductsByBrand(brand);
        if (productEntities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productEntities);
    }

    @PostMapping()
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        if (Boolean.FALSE.equals(productService.isValidProduct(product))) {
            return ResponseEntity.notFound().build();
        }
        return productService.addProduct(product);
    }

}
