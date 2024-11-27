package umc.mission.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.mission.apiPayload.ApiResponse;
import umc.mission.converter.ReviewConverter;
import umc.mission.domain.Review;
import umc.mission.service.reviewservice.ReviewCommandService;
import umc.mission.web.dto.ReviewRequestDto;
import umc.mission.web.dto.ReviewResponseDto;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/reviews")
public class ReviewRestController {


    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDto.ReviewJoinResultDto> join(@RequestBody @Valid ReviewRequestDto.ReviewJoinDto request) {
        Review review = reviewCommandService.JoinReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDto(review));
    }
}
