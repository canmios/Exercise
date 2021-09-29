package com.rei.interview.product.repository;

import com.rei.interview.product.model.Product;

import java.util.*;


public interface ProductRepository {

    void addProduct(Product product);

    void addProducts(Map<String, Product> products);

    Optional<Product> getProduct(String productId);

    Collection<Product> getAll();

}
