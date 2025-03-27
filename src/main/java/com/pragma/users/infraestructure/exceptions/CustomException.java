package com.pragma.users.infraestructure.exceptions;



public class CustomException extends RuntimeException{

    public CustomException (String message){
        super(message);
    }
}
