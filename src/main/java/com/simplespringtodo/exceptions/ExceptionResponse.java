package com.simplespringtodo.exceptions;


/**
 * The type Error response.
 */
public class ExceptionResponse {

    private boolean success;

    private String message;

    /**
     * Instantiates a new Error response.
     *
     * @param message the message
     */
    public ExceptionResponse(String message, boolean success) {
        this.setMessage(message);
        this.setSuccess(success);
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * Sets status code.
     *
     * @param success the status code
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
