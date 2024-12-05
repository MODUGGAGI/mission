package umc.mission.converter;

import org.springframework.data.domain.Page;
import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.membermission.MemberMissionResponseDto;
import umc.mission.web.dto.mission.MissionResponseDto;

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


    public static MemberMissionResponseDto.MemberMissionPreviewDTO toMemberMissionPreviewDTO(MemberMission memberMission) {

        return MemberMissionResponseDto.MemberMissionPreviewDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .build();
    }

    public static MemberMissionResponseDto.MemberMissionPreviewListDTO toMemberMissionPreviewListDTO(Page<MemberMission> memberMissionList) {

        List<MemberMissionResponseDto.MemberMissionPreviewDTO> memberMissionPreviewDTOList
                = memberMissionList.stream().map(MemberMissionConverter::toMemberMissionPreviewDTO).toList();

        return MemberMissionResponseDto.MemberMissionPreviewListDTO.builder()
                .isFirst(memberMissionList.isFirst())
                .isLast(memberMissionList.isLast())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionPreviewDTOList.size())
                .memberMissionList(memberMissionPreviewDTOList)
                .build();
    }

    public static MemberMissionResponseDto.MemberMissionCompleteStatusDTO toMemberMissionCompleteStatusDTO(MemberMission memberMission) {
        return MemberMissionResponseDto.MemberMissionCompleteStatusDTO.builder()
                .userName(memberMission.getMember().getName())
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .reward(memberMission.getMission().getReward())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .build();
    }
}
