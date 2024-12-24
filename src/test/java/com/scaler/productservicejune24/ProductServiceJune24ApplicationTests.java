package com.scaler.productservicejune24;

import com.scaler.productservicejune24.projections.productWithIdAndTitle;
import com.scaler.productservicejune24.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceJune24ApplicationTests {
	@Autowired
	private ProductRepository productRepository;

//	ProductServiceJune24ApplicationTests(ProductRepository productRepository) {
//		this.productRepository = productRepository;
//	}

	@Test
	void contextLoads() {
	}

	@Test
	void testDBQueries() {
		List<productWithIdAndTitle> productWithIdAndTitleList =
				productRepository.randomSearchMethod2();

		for(productWithIdAndTitle productWithIdAndTitle : productWithIdAndTitleList) {
			System.out.println(productWithIdAndTitle.getId()+" "+productWithIdAndTitle.getTitle());
		}

		System.out.println("DEBUG");
	}

}
