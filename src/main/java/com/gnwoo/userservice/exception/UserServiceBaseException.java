package com.gnwoo.userservice.exception;

public class UserServiceBaseException extends RuntimeException {
    public UserServiceBaseException() {
        super();
    }

    public UserServiceBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceBaseException(String message) {
        super(message);
    }

    public UserServiceBaseException(Throwable cause) {
        super(cause);
    }
}
