package com.rei.interview.ui;

import com.rei.interview.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

    private final ProductService productService;

    public UiController(
            ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

}
