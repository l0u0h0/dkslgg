package com.ssafy.dksl.util.exception;

import com.ssafy.dksl.util.exception.common.CustomException;
import org.springframework.http.HttpStatus;

public class MemberTeamCreateException extends CustomException {
    private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    @Override
    public HttpStatus getHttpStatus() { return this.httpStatus; }
    public MemberTeamCreateException() { super("팀에 가입할 수 없습니다."); }
}
