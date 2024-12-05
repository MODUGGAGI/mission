package umc.mission.web.dto.membermission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.mission.domain.enums.MissionStatus;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.mission.MissionResponseDto;

public class MemberMissionResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionJoinResultDto {
        Long memberMissionId;
        MemberResponseDto.MemberDto member;
        MissionResponseDto.MissionDto mission;
        MissionStatus status;
    }


}
