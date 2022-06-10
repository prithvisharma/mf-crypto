package com.mf.crypto.constant;

import org.springframework.http.HttpStatus;

public enum CustomError {
    SERVICE_FAILURE("Service is currently unavailable", HttpStatus.INTERNAL_SERVER_ERROR),
    VENDOR_API_FAILURE("Vendor api failure", HttpStatus.INTERNAL_SERVER_ERROR),
    COIN_NOT_FOUND("Coin not found", HttpStatus.NOT_FOUND),
    INVALID_SYMBOL("Invalid symbol", HttpStatus.BAD_REQUEST),
    INVALID_PAGE_NO("Invalid page no, should be integer value only", HttpStatus.BAD_REQUEST),
    INVALID_PAGE_SIZE("Invalid page size, should be integer value only", HttpStatus.BAD_REQUEST),
    QUERY_PARAM_NOT_SUPPORTED("Query parameter not supported", HttpStatus.EXPECTATION_FAILED),
    QUERY_PARAM_REQUIRED("Query parameter is mandatory", HttpStatus.EXPECTATION_FAILED);

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
