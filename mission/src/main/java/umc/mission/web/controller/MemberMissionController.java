package umc.mission.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.mission.apiPayload.ApiResponse;
import umc.mission.converter.MemberMissionConverter;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.service.membermissionservice.MemberMissionCommandService;
import umc.mission.web.dto.membermission.MemberMissionRequestDto;
import umc.mission.web.dto.membermission.MemberMissionResponseDto;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/membermissions")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/")
    public ApiResponse<MemberMissionResponseDto.MemberMissionJoinResultDto> join(@RequestBody @Valid MemberMissionRequestDto.MemberMissionJoinDto request) {
        MemberMission memberMission = memberMissionCommandService.joinMemberMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toJoinResult(memberMission));
    }
}
