package com.rei.interview.cart.checkout.repository.impl;

import com.rei.interview.cart.checkout.repository.CartRepository;
import com.rei.interview.cart.checkout.model.Cart;
import com.rei.interview.util.Cache;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CartCacheRepository implements CartRepository {

    private final Map<String, Cart> carts = new Cache<>();

    @Override
    public void addCart(Cart cart) {
        carts.put(cart.getCartId().toString(), cart);
    }

    @Override
    public Cart getCart(String cartId) {
        return carts.get(cartId);
    }

}
