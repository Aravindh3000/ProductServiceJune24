package com.scaler.productservicejune24.controllers;

import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.Product;
import com.scaler.productservicejune24.services.ProductService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productId) throws ProductNotFoundException {
        ResponseEntity<Product> response = null;

//        try{
//            Product product = productService.getProductById(productId);
//            response = new ResponseEntity<>(
//                    product,
//                    HttpStatus.OK
//            );
//        }
//        catch (RuntimeException e){
//            response = new ResponseEntity<>(
//
//                    HttpStatus.NOT_FOUND
//            );
//        }

        response = new ResponseEntity<>(
                productService.getProductById(productId),
                HttpStatus.OK
        );

        return response;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/limit/{count}")
    public List<Product> getLimitedData(@PathVariable("count") long count){
        return productService.getLimitedProducts(count);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }
}
