package com.rei.interview.inventory.repository.impl;

import com.rei.interview.inventory.model.ProductInventory;
import com.rei.interview.inventory.repository.InventoryRepository;
import com.rei.interview.util.Cache;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskInventoryRepository implements InventoryRepository {

    private Map<String, List<ProductInventory>> products = new Cache<>();

    @Override
    public void addProduct(String key, List<ProductInventory> productInventories) {
        products.put(key, productInventories);
    }

    @Override
    public List<ProductInventory> getListInventory(String key) {
        return products.get(key);
    }

}
