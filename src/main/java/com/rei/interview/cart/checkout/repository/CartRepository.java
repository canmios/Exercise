package com.rei.interview.cart.checkout.repository;

import com.rei.interview.cart.checkout.model.Cart;

public interface CartRepository {

    void addCart(Cart cart);

    Cart getCart(String cartId);

}
