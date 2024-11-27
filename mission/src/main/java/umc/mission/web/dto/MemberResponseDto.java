package umc.mission.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDto {
        Long memberId;
        LocalDateTime createdAt;
    }
}