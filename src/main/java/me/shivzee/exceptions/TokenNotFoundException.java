package me.shivzee.exceptions;

public class TokenNotFoundException extends Exception {
    public TokenNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
