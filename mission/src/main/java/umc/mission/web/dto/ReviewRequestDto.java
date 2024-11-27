package umc.mission.web.dto;

import lombok.Getter;
import umc.mission.validation.annotation.ExistStore;

public class ReviewRequestDto {

    @Getter
    public static class ReviewJoinDto {
        Long memberId;

        @ExistStore
        Long storeId;
        String body;
        Float score;
    }
}
