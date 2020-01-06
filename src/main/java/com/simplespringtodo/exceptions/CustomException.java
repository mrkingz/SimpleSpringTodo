package com.simplespringtodo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Custom exceptions.
 */
public class CustomException extends RuntimeException {

    private HttpStatus status;

    /**
     * Instantiates a new Custom exceptions.
     *
     * @param message the message
     * @param status  the status
     */
    public CustomException(String message, HttpStatus status) {
        super(message);
        this.setStatus(status);
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ status: ").append(this.getStatus().value()+", ").append("message: ").append(this.getMessage()+" }");

        return builder.toString();
    }
}
