package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class TeamInvalidException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    public TeamInvalidException() {
        super("소속 정보를 가져올 수 없습니다.");
    }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
