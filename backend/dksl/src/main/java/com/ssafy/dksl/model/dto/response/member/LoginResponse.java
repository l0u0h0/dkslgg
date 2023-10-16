package com.ssafy.dksl.model.dto.response.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

// 회원가입 시 필요한 정보
@Builder
@Getter
public class LoginResponse {
    @NotNull
    @JsonProperty(value="member")
    private MemberResponse memberResponse;

    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;

}
