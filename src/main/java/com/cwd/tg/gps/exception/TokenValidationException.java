package com.cwd.tg.gps.exception;

public class TokenValidationException extends RuntimeException {

    public TokenValidationException(String message, Throwable e) {
        super(message, e);
    }
}
