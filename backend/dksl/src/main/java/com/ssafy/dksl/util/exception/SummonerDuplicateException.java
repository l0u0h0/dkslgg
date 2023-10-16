package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class SummonerDuplicateException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public SummonerDuplicateException() {
        super("해당 닉네임을 가진 회원이 이미 존재합니다.");
    }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}