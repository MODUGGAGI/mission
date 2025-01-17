package umc.mission.service.reviewservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mission.apiPayload.code.status.ErrorStatus;
import umc.mission.apiPayload.exception.handler.MemberHandler;
import umc.mission.apiPayload.exception.handler.StoreHandler;
import umc.mission.converter.ReviewConverter;
import umc.mission.domain.Member;
import umc.mission.domain.Review;
import umc.mission.domain.Store;
import umc.mission.repository.MemberRepository;
import umc.mission.repository.ReviewRepository;
import umc.mission.repository.storerepository.StoreRepository;
import umc.mission.web.dto.review.ReviewRequestDto;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    @Override
    @Transactional
    public Review JoinReview(ReviewRequestDto.ReviewJoinDto request, Long storeId) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member, store);

        review.setMember(member);
        review.setStore(store);

        store.addReviewScore(review.getScore());

        return reviewRepository.save(review);
    }
}
