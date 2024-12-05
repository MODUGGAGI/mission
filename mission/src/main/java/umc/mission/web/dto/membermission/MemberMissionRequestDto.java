package umc.mission.web.dto.membermission;

import lombok.Getter;
import umc.mission.domain.enums.MissionStatus;
import umc.mission.validation.annotation.ExistMemberMission;

public class MemberMissionRequestDto {

    @Getter
    @ExistMemberMission
    public static class MemberMissionJoinDto {
        Long memberId;
        Long missionId;
    }
}
