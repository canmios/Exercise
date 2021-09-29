package com.rei.interview.inventory.model;

import com.rei.interview.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ProductInventory {

    private String productId;
    private Location location;
    private Integer quantity;

    public ProductInventory(String productId, Location location, Integer quantity) {
        this.productId = productId;
        this.location = location;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInventory product = (ProductInventory) o;
        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
