package umc.mission.converter;

import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.membermission.MemberMissionResponseDto;
import umc.mission.web.dto.mission.MissionResponseDto;
import umc.mission.web.dto.store.StoreResponseDto;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }

    public static MemberMissionResponseDto.MemberMissionJoinResultDto toJoinResult(MemberMission memberMission) {
        return MemberMissionResponseDto.MemberMissionJoinResultDto.builder()
                .memberMissionId(memberMission.getId())
                .member(MemberResponseDto.MemberDto.builder()
                        .memberId(memberMission.getMember().getId())
                        .name(memberMission.getMember().getName())
                        .build())
                .mission(MissionResponseDto.MissionDto.builder()
                        .missionId(memberMission.getMission().getId())
                        .build())
                .status(memberMission.getStatus())
                .build();
    }

}
