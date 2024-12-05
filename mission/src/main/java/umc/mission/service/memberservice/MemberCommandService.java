package umc.mission.service.memberservice;

import umc.mission.domain.Member;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.web.dto.member.MemberRequestDto;

public interface MemberCommandService {
    Member joinMember(MemberRequestDto.MemberJoinDto request);

    MemberMission changeToCompleteStatus(Long memberId, Long missionId);
}