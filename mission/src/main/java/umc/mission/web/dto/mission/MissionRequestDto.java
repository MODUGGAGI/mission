package umc.mission.web.dto.mission;

import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDto {

    @Getter
    public static class MissionJoinDto {
        Integer reward;
        LocalDate deadline;
        String missionSpec;
    }
}
