package com.incretio.study.otus.architectureandpatterns;

public class HasNoHandlerException extends RuntimeException {

    public HasNoHandlerException() {
        super();
    }

    public HasNoHandlerException(String message) {
        super(message);
    }

    public HasNoHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HasNoHandlerException(Throwable cause) {
        super(cause);
    }

    protected HasNoHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
