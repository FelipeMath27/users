package com.pragma.users.infrastructure.exceptions;

public class InfrastructureException extends RuntimeException{
    public InfrastructureException(String message, Throwable cause){
        super(message, cause);
    }
}
