package com.ssafy.dksl.util.exception;

public class UserNotExistException extends Exception{
    public UserNotExistException() {
    }

    public UserNotExistException(String message) {
        super(message);
    }
}
