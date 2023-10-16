package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class MemberDuplicateException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public MemberDuplicateException() {
        super("해당 아이디를 가진 회원이 이미 존재합니다.");
    }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}