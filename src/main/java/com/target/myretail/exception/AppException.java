package com.target.myretail.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AppException {

    public static final Logger logger = LoggerFactory.getLogger(AppException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundExceptionHandler(ProductNotFoundException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ProductTitleNotFoundException.class)
    public ResponseEntity<ErrorResponse> productTitleNotFoundExceptionHandler(ProductTitleNotFoundException ex) {
        logger.error(ex.getMessage());
        ErrorResponse advice = new ErrorResponse(ex.getMessage());
        return new ResponseEntity(advice, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ProductPriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> productPriceNotFoundExceptionHandler(ProductPriceNotFoundException ex) {
        logger.error(ex.getMessage());
        ErrorResponse advice = new ErrorResponse(ex.getMessage());
        return new ResponseEntity(advice, HttpStatus.NO_CONTENT);
    }

}
