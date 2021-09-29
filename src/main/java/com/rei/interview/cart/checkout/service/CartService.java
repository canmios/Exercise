package com.rei.interview.cart.checkout.service;

import com.rei.interview.cart.checkout.model.Cart;
import com.rei.interview.cart.checkout.repository.CartRepository;
import com.rei.interview.exception.NoInventoryException;
import com.rei.interview.inventory.model.ProductInventory;
import com.rei.interview.inventory.service.InventoryService;
import com.rei.interview.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final InventoryService inventoryService;
    private final CartRepository cartRepository;

    public CartService(InventoryService inventoryService
            , CartRepository cartRepository) {
        this.inventoryService = inventoryService;
        this.cartRepository = cartRepository;
    }

    public Cart addToCart(String cartId, Product product, ProductInventory productInventory)
            throws NoInventoryException {
        // is there enough inventory to sell this product?
        if (!inventoryService.hasSomeProductInStock(product, productInventory.getQuantity()
                        , productInventory.getLocation())) {
            throw new NoInventoryException("No inventory for this product");
        }
        // is there already a cart for this customer?
        Cart cart = cartId == null ? new Cart() : cartRepository.getCart(cartId);
        cart = Optional.ofNullable(cart).orElse(new Cart());
        //is this item already in the cart? If so add to the existing quantity

        cartRepository.addCart(cart);

        cart.getProducts().merge(product, productInventory.getQuantity(), Integer::sum);
        return cart;
    }

}

