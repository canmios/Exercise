package com.rei.interview.product.service;

import com.rei.interview.data.DataService;
import com.rei.interview.product.model.Product;
import com.rei.interview.product.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DataService dataService;

    public ProductService(ProductRepository productRepository, DataService dataService) {
        this.productRepository = productRepository;
        this.dataService = dataService;
    }

    @Scheduled(fixedRateString  = "${scheduled.load}")
    public void populateDataFromCvc() throws IOException {
        productRepository.addProducts(dataService.populateProducts());
    }

    public boolean isValidProduct(@Valid Product product) {
        return StringUtils.isNumeric(product.getProductId())
                && product.getProductId().length() == 6
                && !product.getBrand().isEmpty()
                && !product.getDescription().isEmpty()
                && product.getPrice() != null
                && product.getPrice().compareTo(BigDecimal.ZERO) > 0;
    }

    public ResponseEntity<String> addProduct(Product product) {
        if(this.productRepository.getProduct(product.getProductId()).isEmpty())
        {
            this.productRepository.addProduct(product);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Optional<Product> getProduct(String productId) {
        return productRepository.getProduct(productId);
    }

    public List<Product> getAllProductsByBrand(String brand) {
        return productRepository.getAll()
                .stream()
                .filter(x -> x.getBrand().equals(brand))
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productRepository.getAll());
    }

}
