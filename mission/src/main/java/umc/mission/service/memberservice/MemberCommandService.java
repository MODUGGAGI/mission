package umc.mission.service.memberservice;

import umc.mission.domain.Member;
import umc.mission.web.dto.MemberRequestDto;

public interface MemberCommandService {

    Member joinMember(MemberRequestDto.JoinDto request);
}
