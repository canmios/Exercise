package com.rei.interview.product.repository.impl;

import com.rei.interview.product.repository.ProductRepository;
import com.rei.interview.product.model.Product;
import com.rei.interview.util.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class ProductTaskRepository implements ProductRepository {


    private final Map<String, Product> products = new Cache<>();

    @Override
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    @Override
    public void addProducts(Map<String, Product> productsClone) {
        Set<Map.Entry<String, Product>> entries = productsClone.entrySet();
        for (Map.Entry<String, Product> mapEntry : entries) {
            products.put(mapEntry.getKey(), mapEntry.getValue());
        }
    }

    @Override
    public Optional<Product> getProduct(String productId) {
        return Optional.ofNullable(products
                .get(productId));
    }

    @Override
    public Collection<Product> getAll() {
        return products.values();
    }

}
