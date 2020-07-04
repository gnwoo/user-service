package com.gnwoo.userservice.exception;

public class DuplicateUsernameException extends UserServiceBaseException {
    public DuplicateUsernameException() {
        super();
    }

    public DuplicateUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }

    public DuplicateUsernameException(Throwable cause) {
        super(cause);
    }
}
