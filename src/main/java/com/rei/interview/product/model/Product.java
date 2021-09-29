package com.rei.interview.product.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class Product {

    private String productId;
    private String brand;
    private String description;
    private BigDecimal price;

    public Product(String productId, String brand, String description, BigDecimal price) {
        this.productId = productId;
        this.brand = brand;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "products";
    }
}
