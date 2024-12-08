package umc.mission.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.mission.apiPayload.ApiResponse;
import umc.mission.converter.MissionConverter;
import umc.mission.converter.ReviewConverter;
import umc.mission.converter.StoreConverter;
import umc.mission.domain.Mission;
import umc.mission.domain.Review;
import umc.mission.domain.Store;
import umc.mission.service.missionservice.MissionCommandService;
import umc.mission.service.reviewservice.ReviewCommandService;
import umc.mission.service.storeservice.StoreCommandService;
import umc.mission.service.storeservice.StoreQueryService;
import umc.mission.validation.annotation.CheckPage;
import umc.mission.validation.annotation.ExistStore;
import umc.mission.web.dto.mission.MissionRequestDto;
import umc.mission.web.dto.mission.MissionResponseDto;
import umc.mission.web.dto.review.ReviewRequestDto;
import umc.mission.web.dto.review.ReviewResponseDto;
import umc.mission.web.dto.store.StoreRequestDto;
import umc.mission.web.dto.store.StoreResponseDto;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;
    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDto.StoreJoinResultDto> join(@RequestBody @Valid StoreRequestDto.StoreJoinDto request) {
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDto(store));
    }

    @PostMapping("/{storeId}/review")
    @Operation(summary = "특정 가게에 리뷰 추가 API", description = "특정 가게에 리뷰를 추가하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
    })
    public ApiResponse<ReviewResponseDto.ReviewJoinResultDto> join(@RequestBody @Valid ReviewRequestDto.ReviewJoinDto request, @ExistStore @PathVariable Long storeId) {
        Review review = reviewCommandService.JoinReview(request, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDto(review));
    }

    @PostMapping("/{storeId}/mission")
    @Operation(summary = "특정 가게에 미션 추가 API", description = "특정 가게에 미션을 추가하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
    })
    public ApiResponse<MissionResponseDto.MissionJoinResultDto> join(@RequestBody @Valid MissionRequestDto.MissionJoinDto request, @ExistStore @PathVariable Long storeId) {
        Mission mission = missionCommandService.joinMission(request, storeId);
        return ApiResponse.onSuccess(MissionConverter.toJoinResult(mission));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰 목록을 조회하는 API이며, 페이징을 포함한다. query string으로 page 번호 전달")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name =  "page", description = "조회할 page 번호 입니다.")
    })
    public ApiResponse<StoreResponseDto.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable Long storeId, @CheckPage Integer page) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함한다. query string으로 page 번호 전달")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name =  "page", description = "조회할 page 번호 입니다.")
    })
    public ApiResponse<StoreResponseDto.MissionListDTO> getMissionList(@ExistStore @PathVariable Long storeId, @CheckPage Integer page) {
        Page<Mission> missionList = storeQueryService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.toMissionListDTO(missionList));
    }
}
