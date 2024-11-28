package umc.mission.service.memberservice;

import umc.mission.domain.Member;
import umc.mission.web.dto.member.MemberRequestDto;

public interface MemberCommandService {
    Member joinMember(MemberRequestDto.MemberJoinDto request);
}