package com.scaler.productservicejune24.exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
