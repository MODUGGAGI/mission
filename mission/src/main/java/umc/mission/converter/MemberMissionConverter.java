package umc.mission.converter;

import org.springframework.data.domain.Page;
import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.membermission.MemberMissionResponseDto;
import umc.mission.web.dto.mission.MissionResponseDto;
import umc.mission.web.dto.store.StoreResponseDto;

import java.util.List;

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


    public static MemberMissionResponseDto.MemberMissionDTO toMemberMissionDTO(MemberMission memberMission) {

        return MemberMissionResponseDto.MemberMissionDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .build();
    }

    public static MemberMissionResponseDto.MemberMissionListDTO toMemberMissionListDTO(Page<MemberMission> memberMissionList) {

        List<MemberMissionResponseDto.MemberMissionDTO> memberMissionDTOList
                = memberMissionList.stream().map(MemberMissionConverter::toMemberMissionDTO).toList();

        return MemberMissionResponseDto.MemberMissionListDTO.builder()
                .isFirst(memberMissionList.isFirst())
                .isLast(memberMissionList.isLast())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionDTOList.size())
                .memberMissionList(memberMissionDTOList)
                .build();
    }
}
