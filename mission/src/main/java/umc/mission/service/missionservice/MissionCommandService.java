package umc.mission.service.missionservice;

import umc.mission.domain.Mission;
import umc.mission.web.dto.MissionRequestDto;

public interface MissionCommandService {
    Mission joinMission(MissionRequestDto.MissionJoinDto request);
}