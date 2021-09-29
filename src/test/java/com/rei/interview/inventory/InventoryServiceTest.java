package com.rei.interview.inventory;

import com.rei.interview.Location;
import com.rei.interview.data.DataService;
import com.rei.interview.data.DataInformation;
import com.rei.interview.inventory.model.ProductInventory;
import com.rei.interview.inventory.repository.InventoryRepository;
import com.rei.interview.inventory.service.InventoryService;
import com.rei.interview.product.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class InventoryServiceTest {

    private InventoryService inventoryService;

    @Mock
    private DataService dataService;

    @Mock
    private InventoryRepository inventoryRepository;

    private List<ProductInventory> productsInventory;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        inventoryService = new InventoryService(inventoryRepository, dataService);
        product = DataInformation.getProduct();
        productsInventory = DataInformation.listInventory();
    }


    @Test
    void itShouldBeTrueWhenHasInventoryOnline() {
        given(inventoryRepository.getListInventory(any())).willReturn(productsInventory);
        boolean expected = inventoryService
                .hasInventoryOnline(product, 1);

        Assertions.assertTrue(expected);

    }

    @Test
    void itShouldBeTrueWhenHasInventoryIsNotOnline() {
        given(inventoryRepository.getListInventory(any())).willReturn(productsInventory);
        boolean expected = inventoryService
                .hasInventoryOnline(product, 11);

        Assertions.assertFalse(expected);

    }

    @Test
    void itShouldBeTrueWhenIsInNearbyStores() {
        given(inventoryRepository.getListInventory(any())).willReturn(productsInventory);
        boolean expected = inventoryService
                .hasInventoryInNearbyStores(product, 1, Location.valueOf("SEATTLE"));

        Assertions.assertTrue(expected);

    }

    @Test
    void itShouldBeTrueWhenIsNotInNearbyStores() {
        given(inventoryRepository.getListInventory(any())).willReturn(productsInventory);
        boolean expected = inventoryService
                .hasInventoryInNearbyStores(product, 11, Location.valueOf("SEATTLE"));

        Assertions.assertFalse(expected);

    }

    @Test
    void itShouldFilterWhenHasSomeProductInStock() {
        given(inventoryRepository.getListInventory(any())).willReturn(productsInventory);
        boolean expected = inventoryService
                .hasSomeProductInStock(product, 1, Location.SEATTLE);

        Assertions.assertTrue(expected);

    }

    @Test
    void itShouldFilterWhenHasNotSomeProductInStock() {
        given(inventoryRepository.getListInventory(any())).willReturn(productsInventory);
        boolean expected = inventoryService
                .hasSomeProductInStock(product, 13, Location.SEATTLE);

        Assertions.assertFalse(expected);

    }

    @Test
    void itShouldPopulateList() throws IOException {
        inventoryService.populateDataFromCvc();
        boolean expected = inventoryService
                .hasSomeProductInStock(product, 13, Location.SEATTLE);

        Assertions.assertFalse(expected);

    }

}
