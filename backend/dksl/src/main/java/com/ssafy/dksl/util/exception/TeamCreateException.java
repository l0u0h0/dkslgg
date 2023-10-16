package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class TeamCreateException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
    public TeamCreateException() {
        super("소속을 생성할 수 없습니다.");
    }
}
