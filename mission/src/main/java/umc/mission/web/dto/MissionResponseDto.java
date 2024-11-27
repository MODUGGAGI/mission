package umc.mission.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MissionResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionJoinResultDto {
        Long missionId;
        StoreResponseDto.StoreDto store;
        Integer reward;
        String missionSpec;
        LocalDate deadline;
    }
}
