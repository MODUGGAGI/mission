package umc.mission.web.dto.membermission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.mission.domain.enums.MissionStatus;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.mission.MissionResponseDto;

import java.time.LocalDate;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionListDTO {
        List<MemberMissionResponseDto.MemberMissionDTO> memberMissionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionDTO {
        String storeName;
        MissionStatus status;
        Integer reward;
        LocalDate deadline;
        String missionSpec;
    }
}
