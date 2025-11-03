package edu.causwict.restapi.exception;

public class TooManyPostsException extends RuntimeException {
    public TooManyPostsException(String message) {
        super(message);
    }
}
