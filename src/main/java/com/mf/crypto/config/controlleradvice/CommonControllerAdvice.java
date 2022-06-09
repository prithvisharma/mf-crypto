package com.mf.crypto.config.controlleradvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mf.crypto.dto.ErrorDto;
import com.mf.crypto.exception.ServiceException;

@RestControllerAdvice
public class CommonControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(CommonControllerAdvice.class);

    @ExceptionHandler({ ServiceException.class })
    public ResponseEntity<ErrorDto> handleGeneralException(com.mf.crypto.exception.ServiceException e) {
        logger.error("------------------SERVICE-FAILURE------------------");
        e.printStackTrace();
        logger.error("ERROR: ", e.getMessage());
        return new ResponseEntity<ErrorDto>(e.unpack(), e.getCustomError().getHttpStatus());
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleGeneralException(Exception e) {
        logger.error("------------------------ERROR------------------------");
        e.printStackTrace();
        logger.error("ERROR: ", e.getMessage());
        return new ServiceException().unpack();
    }
}