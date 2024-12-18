package umc.mission.web.dto.review;

import lombok.Getter;

public class ReviewRequestDto {

    @Getter
    public static class ReviewJoinDto {
        Long memberId;
        String body;
        Float score;
    }
}
