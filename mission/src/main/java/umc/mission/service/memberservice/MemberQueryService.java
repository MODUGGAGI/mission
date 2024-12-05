package umc.mission.service.memberservice;

import org.springframework.data.domain.Page;
import umc.mission.domain.Mission;
import umc.mission.domain.Review;
import umc.mission.domain.mapping.MemberMission;

public interface MemberQueryService {

    Page<Review> getMyReviews(Long memberId, Integer page);

    Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page);
}