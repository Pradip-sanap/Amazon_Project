package com.user.Exception;

public class PhoneAlreadyExistsException extends RuntimeException{
    public PhoneAlreadyExistsException(String message) {
        super(message);
    }
}
