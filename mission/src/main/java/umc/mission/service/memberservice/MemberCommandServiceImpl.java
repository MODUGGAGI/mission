package umc.mission.service.memberservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mission.apiPayload.code.status.ErrorStatus;
import umc.mission.apiPayload.exception.handler.FoodCategoryHandler;
import umc.mission.apiPayload.exception.handler.MemberHandler;
import umc.mission.apiPayload.exception.handler.MemberMissionHandler;
import umc.mission.apiPayload.exception.handler.MissionHandler;
import umc.mission.converter.MemberConverter;
import umc.mission.converter.MemberPreferConverter;
import umc.mission.domain.FoodCategory;
import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.domain.enums.MissionStatus;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.domain.mapping.MemberPrefer;
import umc.mission.repository.FoodCategoryRepository;
import umc.mission.repository.MemberRepository;
import umc.mission.repository.MissionRepository;
import umc.mission.repository.membermissionrepository.MemberMissionRepository;
import umc.mission.web.dto.member.MemberRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.MemberJoinDto request) {

        // 이메일 중복 검사
        if (memberRepository.existsByEmail(request.getEmail())) {
            log.error("이메일 중복 발생: {}", request.getEmail());
            throw new IllegalStateException("이미 사용 중인 이메일입니다.");
        }

        Member newMember = MemberConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> foodCategoryRepository.findById(category)
                        .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND))
                )
                .toList();

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        //toMemberPreferList에서는 foodCategory들만을 memberPrefer와 매핑하고 Member는 매핑하지 않는다.

        memberPreferList.forEach(memberPrefer -> memberPrefer.setMember(newMember));
        //양방향 연관관계 설정은 converter가 아닌 service에서 하는게 좋다

        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public MemberMission changeToCompleteStatus(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = memberMissionRepository.findByMemberAndMission(member, mission)
                .orElseThrow(()-> new MemberMissionHandler(ErrorStatus.MISSION_NOT_CHALLENGING));

        if (memberMission.getStatus() == MissionStatus.CHALLENGING) {
            return memberMission.changeStatusToComplete(); //JPA dirtyChecking에 의해 자동으로 값 업데이트
        }

        throw new MemberMissionHandler(ErrorStatus.MISSION_ALREADY_COMPLETE);
    }
}