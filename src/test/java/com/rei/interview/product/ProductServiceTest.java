package com.rei.interview.product;

import com.rei.interview.data.DataInformation;
import com.rei.interview.data.DataService;
import com.rei.interview.product.model.Product;
import com.rei.interview.product.repository.ProductRepository;
import com.rei.interview.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private DataService dataService;

    private Product product;

    private final Collection<Product> products = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository, dataService);
        product = DataInformation.getProduct();
    }

    @Test
    void itShouldGetProduct() {
        given(productRepository.getProduct(any())).willReturn(Optional.of(product));
        Optional<Product> productExpected = productService
                .getProduct(product.getProductId());

        Assertions.assertTrue(productExpected.isPresent());
        Assertions.assertEquals(productExpected.get().getProductId(), product.getProductId());

    }

    @Test
    void itShouldGetProducts() {
        products.add(product);
        given(productRepository.getAll()).willReturn(products);
        List<Product> productsExpected = productService
                .getAllProducts();

        Assertions.assertFalse(productsExpected.isEmpty());
        Assertions.assertEquals(productsExpected, products);

    }

    @Test
    void itShouldValidateProduct() {
        boolean expected = productService
                .isValidProduct(product);
        Assertions.assertTrue(expected);
    }

    @Test
    void itShouldValidateProductWhenIdIsMinor() {
        product.setProductId("1234");
        boolean expected = productService
                .isValidProduct(product);
        Assertions.assertFalse(expected);
    }

}
