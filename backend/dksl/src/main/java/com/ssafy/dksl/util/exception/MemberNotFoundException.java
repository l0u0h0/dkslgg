package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public MemberNotFoundException() {
        super("일치하는 회원을 찾을 수 없습니다.");
    }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
