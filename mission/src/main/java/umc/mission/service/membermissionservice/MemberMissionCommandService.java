package umc.mission.service.membermissionservice;

import umc.mission.domain.mapping.MemberMission;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.membermission.MemberMissionRequestDto;
import umc.mission.web.dto.mission.MissionResponseDto;

public interface MemberMissionCommandService {

    public MemberMission joinMemberMission(MemberMissionRequestDto.MemberMissionJoinDto request);
}
