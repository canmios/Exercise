package com.rei.interview.inventory.service;

import com.rei.interview.data.DataService;
import com.rei.interview.inventory.repository.InventoryRepository;
import com.rei.interview.product.model.Product;
import com.rei.interview.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InventoryService {

    private final InventoryRepository productRepository;
    private final DataService dataService;

    @Value("${key.name}")
    private String load;

    public InventoryService(InventoryRepository productRepository, DataService dataService) {
        this.productRepository = productRepository;
        this.dataService = dataService;
    }

    @Scheduled(fixedRateString  = "${scheduled.load}")
    public void populateDataFromCvc() throws IOException {
        productRepository.addProduct(load, dataService.readInInventory());
    }

    public boolean hasInventoryOnline(Product product, int quantity) {
        return productRepository.getListInventory(load)
                .stream()
                .anyMatch(c -> c.getProductId().equals(product.getProductId())
                        && c.getLocation().name().equals("ONLINE")
                        && c.getQuantity() >= quantity);
    }

    public boolean hasInventoryInNearbyStores(Product product, int quantity, Location currentLocation) {
        return productRepository.getListInventory(load)
                .stream()
                .anyMatch(c -> c.getProductId().equals(product.getProductId()) &&
                c.getLocation().name().equals(currentLocation.name()) &&
                        c.getQuantity() >= quantity);
    }

    public boolean hasSomeProductInStock(Product product, int quantity, Location currentLocation){
        return hasInventoryOnline(product, quantity)
                || hasInventoryInNearbyStores(product, quantity
                        , currentLocation);
    }

}
