package com.ssafy.dksl.model.dto.command.member;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterCommand {
    @NotNull
    @Size(max = 20)
    private String clientId;

    @NotNull
    @Size(min = 8, max = 20)
    private String password;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 8, max = 20)
    private String phone;

    @NotNull
    private String email;
}
