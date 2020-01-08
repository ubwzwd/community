package com.wudi.community.exception;

public enum CustomizedErrorCode implements CustomizedErrorCodeInterface {

    QUESTION_NOT_FOUND("The post doesn't exist.");
    private String message;

    CustomizedErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
