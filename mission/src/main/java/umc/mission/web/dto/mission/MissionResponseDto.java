package umc.mission.web.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.mission.web.dto.store.StoreResponseDto;

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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDto {
        Long missionId;
    }
}
