package se.ifmo.lab4.exceptions;

public class InvalidLoginOrPasswordException extends RuntimeException{
    public InvalidLoginOrPasswordException(String msg) {
        super(msg);
    }
}
