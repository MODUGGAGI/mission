package umc.mission.service.memberservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mission.apiPayload.code.status.ErrorStatus;
import umc.mission.apiPayload.exception.handler.FoodCategoryHandler;
import umc.mission.converter.MemberConverter;
import umc.mission.converter.MemberPreferConverter;
import umc.mission.domain.FoodCategory;
import umc.mission.domain.Member;
import umc.mission.domain.mapping.MemberPrefer;
import umc.mission.repository.FoodCategoryRepository;
import umc.mission.repository.MemberRepository;
import umc.mission.web.dto.MemberRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
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
}