package com.ssafy.dksl.util.exception.common;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends Exception {

    public abstract HttpStatus getHttpStatus();

    public CustomException(String message) { super(message); }
}
