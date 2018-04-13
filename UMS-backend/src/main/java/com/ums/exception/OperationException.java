package com.ums.exception;

import org.springframework.http.HttpStatus;

public class OperationException extends Exception {
    private final HttpStatus status;

    public OperationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
