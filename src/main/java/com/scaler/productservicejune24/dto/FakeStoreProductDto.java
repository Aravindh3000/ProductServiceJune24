package com.scaler.productservicejune24.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
