package umc.mission.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.mission.apiPayload.ApiResponse;
import umc.mission.converter.MissionConverter;
import umc.mission.domain.Mission;
import umc.mission.service.missionservice.MissionCommandService;
import umc.mission.web.dto.MissionRequestDto;
import umc.mission.web.dto.MissionResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDto.MissionJoinResultDto> join(@RequestBody MissionRequestDto.MissionJoinDto request) {
        Mission mission = missionCommandService.joinMission(request);
        return ApiResponse.onSuccess(MissionConverter.toJoinResult(mission));
    }
}
