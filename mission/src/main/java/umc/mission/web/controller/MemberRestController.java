package umc.mission.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.mission.apiPayload.ApiResponse;
import umc.mission.converter.MemberConverter;
import umc.mission.converter.MemberMissionConverter;
import umc.mission.domain.Member;
import umc.mission.domain.Review;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.service.memberservice.MemberCommandService;
import umc.mission.service.memberservice.MemberQueryService;
import umc.mission.validation.annotation.CheckPage;
import umc.mission.web.dto.member.MemberRequestDto;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.membermission.MemberMissionResponseDto;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
@Slf4j
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDto.MemberJoinResultDto> join(@RequestBody @Valid MemberRequestDto.MemberJoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 회원의 리뷰 목록 조회 API", description = "특정 회원의 리뷰 목록을 조회하는 API이며, 페이징을 포함한다. query string으로 page 번호 전달")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "조회할 page 번호 입니다.")
    })
    public ApiResponse<MemberResponseDto.MyReviewListDTO> getReviewList(@PathVariable Long memberId, @CheckPage Integer page) {
        log.info("page={}", page);
        Page<Review> reviewList = memberQueryService.getMyReviews(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.toMyReviewListDTO(reviewList));
    }

    @GetMapping("/{memberId}/missions/challenging")
    @Operation(summary = "특정 회원의 도전중인 미션 목록 조회 API", description = "특정 회원의 도전중인 미션 목록을 조회하는 API이며, 페이징을 포함한다. query string으로 page 번호 전달")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "조회할 page 번호 입니다.")
    })
    public ApiResponse<MemberMissionResponseDto.MemberMissionPreviewListDTO> getChallengingMissionList(@PathVariable Long memberId, @CheckPage Integer page) {
        log.info("page={}", page);
        Page<MemberMission> memberMissionList = memberQueryService.getMyChallengingMissions(memberId, page);
        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionPreviewListDTO(memberMissionList));
    }

    @PatchMapping("/{memberId}/missions/{missionId}")
    @Operation(summary = "특정 회원의 도전중인 미션을 도전완료로 바꾸는 API", description = "특정 회원의 도전중인 미션을 도전완료로 바꾸는 API이다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다!"),
    })
    public ApiResponse<MemberMissionResponseDto.MemberMissionCompleteStatusDTO> changeChallengingMissionToCompleteMission(@PathVariable Long memberId, @PathVariable Long missionId) {
        MemberMission memberMission = memberCommandService.changeToCompleteStatus(memberId, missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionCompleteStatusDTO(memberMission));
    }
}