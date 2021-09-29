package com.rei.interview.cart.checkout.model;

import com.rei.interview.product.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class Cart {

    private UUID cartId = UUID.randomUUID();
    private Map<Product,Integer> products = new ConcurrentHashMap<>();

}
