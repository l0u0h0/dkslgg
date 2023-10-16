package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class LogoutInvalidException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public LogoutInvalidException() { super("재로그인이 필요합니다."); }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
