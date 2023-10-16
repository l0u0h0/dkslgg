package com.ssafy.dksl.util.exception.common;

import org.springframework.http.HttpStatus;

public class InvalidException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    public InvalidException(String domain) {
        super(domain + " 정보를 가져올 수 없습니다.");
    }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
