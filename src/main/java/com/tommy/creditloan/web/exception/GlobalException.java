package com.tommy.creditloan.web.exception;

public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -4750111109997583737L;
    /**
     * error message
     */
    private String message;

    /**
     * error message detail
     */
    private String detailMessage;

    /**
     * construct method
     */
    public GlobalException() {
    }

    public GlobalException(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public GlobalException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public GlobalException setMessage(String message) {
        this.message = message;
        return this;
    }
}