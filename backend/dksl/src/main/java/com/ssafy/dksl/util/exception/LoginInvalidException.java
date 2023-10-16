package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class LoginInvalidException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public LoginInvalidException() { super("아이디 혹은 비밀번호를 틀렸습니다."); }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
