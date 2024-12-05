package umc.mission.service.memberservice;

import org.springframework.data.domain.Page;
import umc.mission.domain.Review;

public interface MemberQueryService {

    public Page<Review> getMyReviews(Long memberId, Integer page);
}