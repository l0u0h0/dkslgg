package com.ssafy.dksl.model.dto.request.team;

import com.ssafy.dksl.model.dto.command.team.CreateTeamCommand;
import com.ssafy.dksl.util.exception.FileNotFoundException;
import com.ssafy.dksl.util.exception.common.CustomException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class CreateTeamRequest {
    // @NotNull
    private String name;
    // @NotNull
    private String description;

    public CreateTeamCommand toCreateTeamCommand(String token, MultipartFile img) throws CustomException {
        if (img == null) {
            throw new FileNotFoundException();
        }
        String contentType = img.getContentType();  // 확장자 타입
        if (contentType == null || contentType.trim().equals("")) {  // 확장자 타입이 없을 경우
            System.out.println("확장자 타입 X");
            throw new FileNotFoundException();
        } else if (!contentType.contains("image/jpeg") && !contentType.contains("image/png")) {  // 확장자 타입이 jpg나 png가 아닐 경우
            throw new FileNotFoundException();
        }

        return CreateTeamCommand.builder()
                .name(this.getName())
                .description(this.getDescription())
                .accessToken(token)
                .img(img)
                .build();
    }
}
