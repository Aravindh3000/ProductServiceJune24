package com.scaler.productservicejune24.controllers;

import com.scaler.productservicejune24.exceptions.NotEnoughProductInfoException;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.Product;
import com.scaler.productservicejune24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
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

    ProductController(@Qualifier("FakeStoreAPI") ProductService productService) {
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

    @GetMapping("/priceGreaterThan/{price}")
    public List<Product> getProductByPriceGreaterThan(@PathVariable("price") double price) {
        return productService.getProductsPriceGreaterThan(price);
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
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product) throws NotEnoughProductInfoException, ProductNotFoundException {
        return productService.replaceProduct(id, product);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ProductNotFoundException, NotEnoughProductInfoException {

        return new ResponseEntity<Product>(
                productService.createProduct(product),
                HttpStatus.OK
        );
    }

}
