package com.ssafy.dksl.model.dto.request.member;

import com.ssafy.dksl.model.dto.command.member.RegisterCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Builder
@Getter
@NoArgsConstructor
public class RegisterRequest {
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

    public RegisterCommand toRegisterCommand() {
        return RegisterCommand.builder()
                .clientId(this.getClientId())
                .password(this.getPassword())
                .phone(this.getPhone())
                .name(this.getName())
                .email(this.getEmail())
                .build();
    }
}
