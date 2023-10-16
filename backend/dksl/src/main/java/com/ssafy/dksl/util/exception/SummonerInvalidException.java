package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class SummonerInvalidException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    public SummonerInvalidException() {
        super("소환사 정보를 가져올 수 없습니다.");
    }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
