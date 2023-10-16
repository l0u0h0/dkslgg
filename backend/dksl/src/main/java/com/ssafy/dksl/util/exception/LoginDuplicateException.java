package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class LoginDuplicateException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public LoginDuplicateException() { super("중복 로그인 하였습니다."); }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
