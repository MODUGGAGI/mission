package umc.mission.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.mission.apiPayload.ApiResponse;
import umc.mission.converter.MemberConverter;
import umc.mission.domain.Member;
import umc.mission.service.memberservice.MemberCommandService;
import umc.mission.web.dto.MemberRequestDto;
import umc.mission.web.dto.MemberResponseDto;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDto.MemberJoinResultDto> join(@RequestBody @Valid MemberRequestDto.MemberJoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(member));
    }
}