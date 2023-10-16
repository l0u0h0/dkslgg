package com.ssafy.dksl.model.dto.command.member;

import com.ssafy.dksl.model.dto.command.common.TokenCommand;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TeamMemberCommand extends TokenCommand {
    private String teamName;
}
