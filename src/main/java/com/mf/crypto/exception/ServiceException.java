package com.mf.crypto.exception;

import com.mf.crypto.constant.CustomError;
import com.mf.crypto.dto.ErrorDto;

public class ServiceException extends Exception {

    protected final CustomError customError;

    public ServiceException(CustomError customError) {
        super(customError.getMessage());
        this.customError = customError;
    }

    public String getCode() {
        return customError.getCode();
    }

    public String getMessage() {
        return customError.getMessage();
    }

    public ServiceException() {
        this(CustomError.SERVICE_FAILURE);
    }

    public CustomError getCustomError() {
        return this.customError;
    }

    public ErrorDto unpack() {
        return new ErrorDto(this.customError);
    }
}
