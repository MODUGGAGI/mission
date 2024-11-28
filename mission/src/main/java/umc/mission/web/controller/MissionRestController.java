package umc.mission.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.mission.apiPayload.ApiResponse;
import umc.mission.converter.MissionConverter;
import umc.mission.domain.Mission;
import umc.mission.service.missionservice.MissionCommandService;
import umc.mission.web.dto.mission.MissionRequestDto;
import umc.mission.web.dto.mission.MissionResponseDto;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDto.MissionJoinResultDto> join(@RequestBody @Valid MissionRequestDto.MissionJoinDto request) {
        Mission mission = missionCommandService.joinMission(request);
        return ApiResponse.onSuccess(MissionConverter.toJoinResult(mission));
    }
}
