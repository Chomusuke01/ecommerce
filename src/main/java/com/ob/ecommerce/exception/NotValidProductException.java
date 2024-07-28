package com.ob.ecommerce.exception;

public class NotValidProductException extends RuntimeException{

    public NotValidProductException(String field){
        super("Not valid product, missing field: " + field);
    }

}
