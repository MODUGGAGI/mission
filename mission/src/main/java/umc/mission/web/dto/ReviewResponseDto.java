package umc.mission.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewJoinResultDto {
        Long reviewId;
        MemberResponseDto.MemberDto member;
        StoreResponseDto.StoreDto store;
        String body;
        Float score;
    }
}
