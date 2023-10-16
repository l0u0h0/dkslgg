package com.ssafy.dksl.model.dto.command.lbti;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter
public class SetLbtiCommand {
    private String accessToken;
    @NotNull
    private List<Long> selectedIndexList;
}
