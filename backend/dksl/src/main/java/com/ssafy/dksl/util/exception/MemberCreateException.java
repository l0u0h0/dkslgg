package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class MemberCreateException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    public MemberCreateException() { super("회원을 생성할 수 없습니다."); }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
