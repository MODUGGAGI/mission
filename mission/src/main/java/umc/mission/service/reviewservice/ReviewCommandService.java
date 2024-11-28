package umc.mission.service.reviewservice;

import umc.mission.domain.Review;
import umc.mission.web.dto.review.ReviewRequestDto;

public interface ReviewCommandService {
    Review JoinReview(ReviewRequestDto.ReviewJoinDto request);
}
