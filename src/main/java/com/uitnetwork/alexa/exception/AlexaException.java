package com.uitnetwork.alexa.exception;

public class AlexaException extends RuntimeException {

    public AlexaException() {
    }

    public AlexaException(String message) {
        super(message);
    }

    public AlexaException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlexaException(Throwable cause) {
        super(cause);
    }

    public AlexaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
