package umc.mission.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.store.StoreResponseDto;

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
