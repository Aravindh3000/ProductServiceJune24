package com.scaler.productservicejune24.exceptions;

public class NotEnoughProductInfoException extends Exception{
    public NotEnoughProductInfoException(String errorMessage){
        super(errorMessage);
    }
}
