package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
    public TeamNotFoundException() {
        super("일치하는 소속을 찾을 수 없습니다.");
    }
}
