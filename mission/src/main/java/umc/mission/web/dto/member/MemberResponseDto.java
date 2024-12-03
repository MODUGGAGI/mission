package umc.mission.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.mission.web.dto.store.StoreResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberJoinResultDto {
        Long memberId;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberDto {
        Long memberId;
        String name;
    }

}