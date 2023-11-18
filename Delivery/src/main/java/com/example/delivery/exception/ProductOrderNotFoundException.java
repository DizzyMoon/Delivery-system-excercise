package com.example.delivery.exception;

public class ProductOrderNotFoundException extends RuntimeException{
    public ProductOrderNotFoundException(String message) {
        super(message);
    }
}
