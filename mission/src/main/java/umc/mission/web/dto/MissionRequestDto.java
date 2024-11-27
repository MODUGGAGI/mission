package umc.mission.web.dto;

import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDto {

    @Getter
    public static class MissionJoinDto {
        Long storeId;
        Integer reward;
        LocalDate deadline;
        String missionSpec;
    }
}
