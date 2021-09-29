package com.rei.interview.inventory.repository;

import com.rei.interview.inventory.model.ProductInventory;

import java.util.Collection;
import java.util.List;

public interface InventoryRepository {

    void addProduct(String key, List<ProductInventory> productInventories);

    List<ProductInventory> getListInventory(String key);

}
