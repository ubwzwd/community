package com.wudi.community.exception;

public class CustomizedException extends RuntimeException {
    private String message;

    public CustomizedException(CustomizedErrorCodeInterface errorCodeInterface) {
        this.message = errorCodeInterface.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
