package com.gnwoo.userservice.exception;

public class Require2FAException extends RuntimeException {
    public Require2FAException() {
        super();
    }

    public Require2FAException(String message, Throwable cause) {
        super(message, cause);
    }

    public Require2FAException(String message) {
        super(message);
    }

    public Require2FAException(Throwable cause) {
        super(cause);
    }
}
