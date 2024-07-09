package com.scaler.productservicejune24.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/products"))
public class ProductController {
    @GetMapping("/all")
    public String getAllProducts(){
        return "Hello from ProductServiceJune24";
    }

}
