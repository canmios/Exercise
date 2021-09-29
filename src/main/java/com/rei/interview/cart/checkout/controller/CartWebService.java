package com.rei.interview.cart.checkout.controller;

import com.rei.interview.cart.checkout.model.Cart;
import com.rei.interview.cart.checkout.service.CartService;
import com.rei.interview.exception.NoInventoryException;
import com.rei.interview.inventory.model.ProductInventory;
import com.rei.interview.product.model.Product;
import com.rei.interview.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartWebService {

    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public CartWebService(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<Cart> addToCart(@PathParam("cartId") String cartId,
                                    @RequestBody ProductInventory productInventory)
            throws NoInventoryException {
        Optional<Product> product = productService.getProduct(productInventory.getProductId());
        if(product.isPresent()){
            Cart cart = cartService.addToCart(cartId, product.get(), productInventory);
            return ResponseEntity.ok(cart);
        }
        return ResponseEntity.notFound().build();
    }

}
