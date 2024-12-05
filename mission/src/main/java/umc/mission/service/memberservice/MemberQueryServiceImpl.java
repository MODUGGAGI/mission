package umc.mission.service.memberservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mission.domain.Member;
import umc.mission.domain.Review;
import umc.mission.domain.Store;
import umc.mission.repository.MemberRepository;
import umc.mission.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    @Override
    public Page<Review> getMyReviews(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        Page<Review> memberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return memberPage;
    }
}