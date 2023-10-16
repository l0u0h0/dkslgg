package com.ssafy.dksl.util.exception.common;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
    public NotFoundException(String domain) {
        super(domain + " 정보를 찾을 수 없습니다.");
    }
}
