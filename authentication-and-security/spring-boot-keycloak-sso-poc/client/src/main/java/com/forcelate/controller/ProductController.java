package com.forcelate.controller;

import com.forcelate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", this.productService.findAll());
        return "products";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/index";
    }
}
