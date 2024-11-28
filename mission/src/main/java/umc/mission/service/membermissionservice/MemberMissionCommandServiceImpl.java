package umc.mission.service.membermissionservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.mission.apiPayload.code.status.ErrorStatus;
import umc.mission.apiPayload.exception.handler.MemberHandler;
import umc.mission.apiPayload.exception.handler.MemberMissionHandler;
import umc.mission.apiPayload.exception.handler.MissionHandler;
import umc.mission.converter.MemberMissionConverter;
import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.repository.membermissionrepository.MemberMissionRepository;
import umc.mission.repository.MemberRepository;
import umc.mission.repository.MissionRepository;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.membermission.MemberMissionRequestDto;
import umc.mission.web.dto.mission.MissionResponseDto;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMission joinMemberMission(MemberMissionRequestDto.MemberMissionJoinDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);

        memberMission.setMember(member);
        memberMission.setMission(mission);

        return memberMissionRepository.save(memberMission);
    }
}
