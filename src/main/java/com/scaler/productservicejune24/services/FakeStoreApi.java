package com.scaler.productservicejune24.services;

import com.scaler.productservicejune24.dto.FakeStoreProductDto;
import com.scaler.productservicejune24.exceptions.NotEnoughProductInfoException;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.Category;
import com.scaler.productservicejune24.models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreAPI") // this marks this class as special so bean created
public class FakeStoreApi implements ProductService{
    // fetch data from fake store site

    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreApi(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);

        if(product != null) {
            return product;
        }

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
                // receives data with same type class
        );

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        product = convertDtoToProduct(fakeStoreProductDto);
//        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, product);
        redisTemplate.opsForValue().set("PRODUCT_" + id, product, Duration.ofMinutes(30));

        return product;
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

    @Override
    public List<Product> getLimitedProducts(long count) {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products?limit=" + count,
                FakeStoreProductDto[].class
        );

        List <Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product product = convertDtoToProduct(fakeStoreProductDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        // Patch

        // Creating request body using fakeStoreProductDto
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreProductDto(id, product);

//        FakeStoreProductDto response = restTemplate.patchForObject(
//                "https://fakestoreapi.com/products/" + id, fakeStoreProductDto, FakeStoreProductDto.class);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);

        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertDtoToProduct(response);
    }

    @Override
    public Product replaceProduct(long id, Product product) {

        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreProductDto(id, product);

//        FakeStoreProductDto response = restTemplate.patchForObject(
//                "https://fakestoreapi.com/products/" + id, fakeStoreProductDto, FakeStoreProductDto.class);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);

        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT, requestCallback, responseExtractor);

        return convertDtoToProduct(response);
    }

    @Override
    public List<Product> getProductsPriceGreaterThan(double price) {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) throws NotEnoughProductInfoException {
        return null;
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

    public FakeStoreProductDto convertProductToFakeStoreProductDto(long id, Product product){
        // Creating request body using fakeStoreProductDto
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        return fakeStoreProductDto;
    }
}
