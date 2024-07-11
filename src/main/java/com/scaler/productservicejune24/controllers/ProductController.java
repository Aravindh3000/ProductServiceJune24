package com.scaler.productservicejune24.controllers;

import com.scaler.productservicejune24.models.Product;
import com.scaler.productservicejune24.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(("/products"))
public class ProductController {

    ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long productId){
        return productService.getProductById(productId);
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
