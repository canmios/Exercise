package com.rei.interview.cart;

import com.rei.interview.cart.checkout.model.Cart;
import com.rei.interview.cart.checkout.repository.CartRepository;
import com.rei.interview.cart.checkout.service.CartService;
import com.rei.interview.data.DataInformation;
import com.rei.interview.exception.NoInventoryException;
import com.rei.interview.inventory.model.ProductInventory;
import com.rei.interview.inventory.service.InventoryService;
import com.rei.interview.product.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;


class CartServiceTest {

    private CartService cartService;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private CartRepository cartRepository;

    private Product product;

    private ProductInventory productInventory;

    private Cart cart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService(inventoryService, cartRepository);
        product = DataInformation.getProduct();
        productInventory = DataInformation.getProductInventory();
        cart = DataInformation.getCart();
    }


    @Test
    void itShouldThrowBusinessLogicExceptionWhenInvalidProduct() {
        given(inventoryService.hasSomeProductInStock(any(), anyInt(), any())).willReturn(false);
        NoInventoryException thrown = assertThrows(
                NoInventoryException.class,
                () -> cartService.addToCart("cartId", product, productInventory)
        );

        assertTrue(thrown.getMessage().contains("No inventory for this product"));

    }

    @Test
    void itShouldCreateCartWhenInventoryIsAvailable()
            throws NoInventoryException {
        given(inventoryService.hasSomeProductInStock(any(), anyInt(), any())).willReturn(true);
        given(cartRepository.getCart(any())).willReturn(cart);
        Cart expected = cartService.addToCart("1234", product, productInventory);

        Assertions.assertEquals(expected, cart);

    }

}
