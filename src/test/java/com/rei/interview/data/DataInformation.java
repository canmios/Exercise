package com.rei.interview.data;

import com.rei.interview.Location;
import com.rei.interview.cart.checkout.model.Cart;
import com.rei.interview.inventory.model.ProductInventory;
import com.rei.interview.product.model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataInformation {

    public static Product getProduct(){
       return new Product("123487"
               ,"736"
               , "34"
               , BigDecimal.TEN);
    }

    public static ProductInventory getProductInventory(){
        return new ProductInventory("1234"
                , Location.valueOf("SEATTLE")
                , Integer.MIN_VALUE);
    }

    public static Cart getCart(){
        Cart cart = new Cart();
        Map<Product,Integer> products = new ConcurrentHashMap<>();
        cart.setCartId(UUID.randomUUID());
        products.put(getProduct(), 1);
        cart.setProducts(products);
        return cart;
    }

    public static List<ProductInventory> listInventory(){
        List<ProductInventory> productInventories = new ArrayList<>();
        productInventories.add(new ProductInventory("123487"
                , Location.valueOf("SEATTLE")
                , 10));
        productInventories.add(new ProductInventory("123464"
                , Location.valueOf("ONLINE")
                , 8));
        productInventories.add(new ProductInventory("15674"
                , Location.valueOf("ONLINE")
                , 7));
        productInventories.add(new ProductInventory("123487"
                , Location.valueOf("ONLINE")
                , 10));

        return productInventories;
    }

}
