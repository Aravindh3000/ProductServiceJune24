package com.scaler.productservicejune24.services;

import com.scaler.productservicejune24.exceptions.NotEnoughProductInfoException;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.Product;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.util.List;

public interface ProductService {
    Product getProductById(long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    List<Product> getLimitedProducts(long count);

    Product updateProduct(long id, Product product) throws ProductNotFoundException;

    Product replaceProduct(long id, Product product) throws NotEnoughProductInfoException, ProductNotFoundException;

    List<Product> getProductsPriceGreaterThan(double price);

    Product createProduct(Product product) throws NotEnoughProductInfoException;
}
