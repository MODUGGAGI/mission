package umc.mission.web.dto.review;

import lombok.Getter;
import umc.mission.validation.annotation.ExistStore;

public class ReviewRequestDto {

    @Getter
    public static class ReviewJoinDto {
        Long memberId;
        String body;
        Float score;
    }
}
