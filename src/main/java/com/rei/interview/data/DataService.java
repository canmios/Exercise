package com.rei.interview.data;

import com.rei.interview.Location;
import com.rei.interview.inventory.model.ProductInventory;
import com.rei.interview.product.model.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.*;

@Service
public class DataService {

    private static final String PRODUCT_ID = "productId";
    private static final String LOCATION = "location";
    private static final String QUANTITY = "quantity";
    private static final String BRAND = "brand";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";

    /**
     * Populates the product inventory repository with data from products_inventory.txt
     *
     */
    public List<ProductInventory> readInInventory() throws IOException {
        List<ProductInventory> inventories = new ArrayList<>();
        try (Reader in = new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/product_inventory.csv")))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(PRODUCT_ID, LOCATION, QUANTITY)
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord csvRecord : records) {
                inventories.add(new ProductInventory(csvRecord.get(PRODUCT_ID)
                        , Location.valueOf(csvRecord.get(LOCATION))
                        , Integer.parseInt(csvRecord.get(QUANTITY))));

            }
            return inventories;
        }
    }

    /**
     * Populates the product repository with data from products.txt
     *
     */
    public Map<String, Product> populateProducts() throws IOException {
        Map<String, Product> products = new HashMap<>() {
        };
        try(Reader in = new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/products.csv")))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(PRODUCT_ID, BRAND, DESCRIPTION, PRICE)
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord csvRecord : records) {
                Product product = new Product(csvRecord.get(PRODUCT_ID)
                        , csvRecord.get(BRAND), csvRecord.get(DESCRIPTION),
                        new BigDecimal(csvRecord.get(PRICE)));
                products.put(product.getProductId(), product);
            }
        }
        return products;
    }

}
