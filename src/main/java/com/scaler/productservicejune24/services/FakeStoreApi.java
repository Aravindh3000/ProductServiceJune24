package com.scaler.productservicejune24.services;

import com.scaler.productservicejune24.dto.FakeStoreProductDto;
import com.scaler.productservicejune24.models.Category;
import com.scaler.productservicejune24.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service // this marks this class as special so bean created
public class FakeStoreApi implements ProductService{
    // fetch data from fake store site

    private RestTemplate restTemplate;

    public FakeStoreApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
                // receives data with same type class
        );

        return convertDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        //
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        // convert into list of products
        List <Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product product = convertDtoToProduct(fakeStoreProductDto);
            products.add(product);
        }

        return products;
    }

    public Product convertDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        // convert the dto data to product type
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        // Setting category
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        category.setDescription(fakeStoreProductDto.getDescription());

        product.setCategory(category);
        return product;
    }
}
