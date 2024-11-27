package umc.mission.converter;

import umc.mission.domain.Member;
import umc.mission.domain.Region;
import umc.mission.domain.Review;
import umc.mission.domain.Store;
import umc.mission.web.dto.*;

public class ReviewConverter {

    public static ReviewResponseDto.ReviewJoinResultDto toJoinResultDto(Review review) {
        return ReviewResponseDto.ReviewJoinResultDto.builder()
                .reviewId(review.getId())
                .store(StoreResponseDto.StoreDto.builder()
                        .storeId(review.getStore().getId())
                        .name(review.getStore().getName())
                        .build())
                .member(MemberResponseDto.MemberDto.builder()
                        .memberId(review.getMember().getId())
                        .name(review.getMember().getName())
                        .build())
                .body(review.getBody())
                .score(review.getScore())
                .build();
    }

    public static Review toReview(ReviewRequestDto.ReviewJoinDto request, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }
}
