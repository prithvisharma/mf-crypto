package com.mf.crypto.constant;

import org.springframework.http.HttpStatus;

public enum CustomError {
    SERVICE_FAILURE("Service is currently unavailable", HttpStatus.INTERNAL_SERVER_ERROR),
    VENDOR_API_FAILURE("Vendor api failure", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    private CustomError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return this.toString();
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
