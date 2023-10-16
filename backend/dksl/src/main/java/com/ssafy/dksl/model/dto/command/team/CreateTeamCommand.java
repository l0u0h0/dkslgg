package com.ssafy.dksl.model.dto.command.team;

import com.ssafy.dksl.model.entity.Member;
import com.ssafy.dksl.model.entity.Team;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
public class CreateTeamCommand {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String accessToken;
    @NotNull
    private MultipartFile img;
}
