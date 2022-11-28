package se.ifmo.lab4.exceptions;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String msg) {
        super(msg);
    }
}
