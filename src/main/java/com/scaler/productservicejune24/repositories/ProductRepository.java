package com.scaler.productservicejune24.repositories;

import com.scaler.productservicejune24.models.Product;
import com.scaler.productservicejune24.projections.productWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProductById(long id);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> getAllByTitleLike(String title);

    List<Product> getAllByTitleIgnoreCase(String title);

    List<Product> findTopByTitleLike(String title);

    List<Product> findAllByTitleContainsAndPriceGreaterThan(String title, double price);

    List<Product> findAllByTitleContainsOrderByTitle(String title);

    @Query("select p from Product p where p.price > :price")
    List<Product> getProductsPriceGreaterThan(double price);

    @Query("select p.id id, p.title title from Product p")
    List<productWithIdAndTitle> randomSearchMethod();

    @Query(value = "select * from product p", nativeQuery = true)
    List<productWithIdAndTitle> randomSearchMethod2();

}
