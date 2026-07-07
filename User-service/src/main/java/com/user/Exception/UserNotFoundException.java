package com.user.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String mesg){
        super(mesg);
    }
}
