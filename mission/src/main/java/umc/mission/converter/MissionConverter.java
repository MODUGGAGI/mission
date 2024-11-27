package umc.mission.converter;

import umc.mission.domain.Mission;
import umc.mission.domain.Store;
import umc.mission.web.dto.MissionRequestDto;
import umc.mission.web.dto.MissionResponseDto;
import umc.mission.web.dto.StoreResponseDto;

public class MissionConverter {

    public static MissionResponseDto.MissionJoinResultDto toJoinResult(Mission mission) {
        return MissionResponseDto.MissionJoinResultDto.builder()
                .missionId(mission.getId())
                .store(StoreResponseDto.StoreDto.builder()
                        .storeId(mission.getStore().getId())
                        .name(mission.getStore().getName())
                        .build())
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .build();
    }

    public static Mission toMission(MissionRequestDto.MissionJoinDto request, Store store) {
        return Mission.builder()
                .store(store)
                .reward(request.getReward())
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .build();
    }
}
