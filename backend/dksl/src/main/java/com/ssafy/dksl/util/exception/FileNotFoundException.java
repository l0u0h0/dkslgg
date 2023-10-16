package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public FileNotFoundException() {
        super("파일이 올바르지 않습니다.");
    }

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
}
