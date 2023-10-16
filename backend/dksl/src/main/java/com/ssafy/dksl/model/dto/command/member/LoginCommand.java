package com.ssafy.dksl.model.dto.command.member;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginCommand {
    @NotNull
    @Size(min = 3, max = 20)
    private String clientId;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;
}
