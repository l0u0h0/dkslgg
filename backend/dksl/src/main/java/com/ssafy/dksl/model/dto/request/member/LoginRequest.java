package com.ssafy.dksl.model.dto.request.member;

import com.ssafy.dksl.model.dto.command.member.LoginCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    @Size(max = 20)
    private String clientId;

    @NotNull
    @Size(min = 8, max = 20)
    private String password;

    public LoginCommand toLoginCommand() {
        return LoginCommand.builder()
                .clientId(this.getClientId())
                .password(this.getPassword())
                .build();
    }
}
