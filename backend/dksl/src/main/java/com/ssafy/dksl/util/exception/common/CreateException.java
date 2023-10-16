package com.ssafy.dksl.util.exception.common;

import org.springframework.http.HttpStatus;

public class CreateException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    public CreateException() {
        super("정보를 생성할 수 없습니다.");
    }
    public CreateException(String domain) {
        super(domain + " 정보를 생성할 수 없습니다.");
    }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
