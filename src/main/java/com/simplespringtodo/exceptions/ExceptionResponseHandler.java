package com.simplespringtodo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The type Exception response handler.
 */
@RestControllerAdvice
public class ExceptionResponseHandler {

    /**
     * Exception response handler response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> exceptionResponseHandler(CustomException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), !ex.getStatus().isError());

        return new ResponseEntity<>(response, ex.getStatus());
    }
}
