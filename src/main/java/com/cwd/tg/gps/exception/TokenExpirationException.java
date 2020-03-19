package com.cwd.tg.gps.exception;

public class TokenExpirationException extends RuntimeException {

    public TokenExpirationException(String message, Throwable e) {
        super(message, e);
    }
}
