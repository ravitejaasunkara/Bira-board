package com.bira.board.utils;

public class BiraGenericException extends Exception{

    private String message;

    public BiraGenericException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BiraGenericException(String message) {
        super(message);
    }
}
