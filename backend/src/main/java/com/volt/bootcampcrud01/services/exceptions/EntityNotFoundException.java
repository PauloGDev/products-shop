package com.volt.bootcampcrud01.services.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message){
        super(message);

    }
}
