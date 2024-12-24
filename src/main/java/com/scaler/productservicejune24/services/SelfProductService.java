package com.scaler.productservicejune24.services;

import com.scaler.productservicejune24.exceptions.NotEnoughProductInfoException;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.Category;
import com.scaler.productservicejune24.models.Product;
import com.scaler.productservicejune24.repositories.CategoryRepository;
import com.scaler.productservicejune24.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findProductById(id);

        if (product.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }

        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> getLimitedProducts(long count) {
        return List.of();
    }

    // PATCH
    @Override
    public Product updateProduct(long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findProductById(id);

        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        Product dbProduct = productOptional.get();

        if(product.getTitle() != null) {
            dbProduct.setTitle(product.getTitle());
        }
        if(product.getPrice() != 0.0) {
            dbProduct.setPrice(product.getPrice());
        }
        if(product.getCategory() != null) {
            dbProduct.setCategory(product.getCategory());
        }

        productRepository.save(dbProduct);

        return dbProduct;
    }

    @Override
    public Product replaceProduct(long id, Product product) throws NotEnoughProductInfoException, ProductNotFoundException {

        Optional<Product> productOptional = productRepository.findProductById(id);

        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        Product dbProduct = productOptional.get();
        if(product.getTitle() != null && product.getPrice() != 0.0 && product.getCategory() != null) {
            dbProduct.setTitle(product.getTitle());
            dbProduct.setPrice(product.getPrice());
            dbProduct.setCategory(product.getCategory());

            productRepository.save(dbProduct);
        }
        else{
            throw new NotEnoughProductInfoException("Not enough product info");
        }

        return dbProduct;
    }

    @Override
    public List<Product> getProductsPriceGreaterThan(double price) {
        return productRepository.getProductsPriceGreaterThan(price);
    }

    @Override
    public Product createProduct(Product product) throws NotEnoughProductInfoException {

//        Category category = product.getCategory();
//        if(category.getId() == 0) {
//            category = categoryRepository.save(category);
//        }
//        product.setCategory(category);

        return productRepository.save(product);
    }
}
