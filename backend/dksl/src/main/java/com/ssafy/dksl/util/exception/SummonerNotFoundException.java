package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class SummonerNotFoundException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
    public SummonerNotFoundException() {
        super("일치하는 닉네임을 찾을 수 없습니다.");
    }
}
