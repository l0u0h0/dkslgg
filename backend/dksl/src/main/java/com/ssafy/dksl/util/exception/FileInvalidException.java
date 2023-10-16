package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class FileInvalidException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    public FileInvalidException() {
        super("파일 정보를 가져올 수 없습니다.");
    }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
