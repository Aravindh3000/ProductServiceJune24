package com.scaler.productservicejune24.controllerAdvice;

import com.scaler.productservicejune24.dto.ExceptionDto;
import com.scaler.productservicejune24.exceptions.NotEnoughProductInfoException;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// To handle exceptions from all controllers
public class globalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("ArithmeticException");
        exceptionDto.setSolution("Try again with valid inputs");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException(){
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                "ArrayIndexOutOfBound from globalHandler",
                HttpStatus.BAD_REQUEST
        );
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Product Not Found");
        exceptionDto.setSolution("Try again with valid inputs");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return responseEntity;
    }

    @ExceptionHandler(NotEnoughProductInfoException.class)
    public ResponseEntity<ExceptionDto> handleNotEnoughProductInfoException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Not Enough Product Info");
        exceptionDto.setSolution("Try again with valid inputs");
        return new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
    }
}
