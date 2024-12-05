package umc.mission.service.memberservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mission.apiPayload.code.status.ErrorStatus;
import umc.mission.apiPayload.exception.handler.MemberHandler;
import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.domain.Review;
import umc.mission.domain.Store;
import umc.mission.domain.enums.MissionStatus;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.repository.MemberRepository;
import umc.mission.repository.ReviewRepository;
import umc.mission.repository.membermissionrepository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<Review> getMyReviews(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<Review> memberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return memberPage;
    }

    @Override
    public Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        return memberMissionRepository.findAllByMemberAndStatus(member, PageRequest.of(page, 10), MissionStatus.CHALLENGING);
    }
}