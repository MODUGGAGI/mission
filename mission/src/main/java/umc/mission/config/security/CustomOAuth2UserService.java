package umc.mission.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.mission.domain.Member;
import umc.mission.domain.enums.Gender;
import umc.mission.domain.enums.MemberStatus;
import umc.mission.domain.enums.Role;
import umc.mission.domain.enums.SocialType;
import umc.mission.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // OAuth2 서비스 구분 (google, naver 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = SocialType.valueOf(registrationId.toUpperCase());

        // 유저 정보 추출
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String providerId = oAuth2User.getAttribute("sub");  // 구글의 경우 sub가 유저 고유 ID
        String loginId = socialType.name() + "_" + providerId;
        String gender = oAuth2User.getAttribute("gender");
        Gender memberGender = "male".equals(gender) ? Gender.MALE : ("female".equals(gender) ? Gender.FEMALE : Gender.NONE);

        // DB에 유저 정보 저장 또는 업데이트
        Member member = saveOrUpdateUser(email, name, providerId, loginId, socialType, memberGender);

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }

    private Member saveOrUpdateUser(String email, String name, String providerId,
                                    String loginId, SocialType socialType, Gender memberGender) {
        Member member = (Member) memberRepository.findByLoginId(loginId)
                .orElseGet(()->
                {
                    Member newMember = Member.builder()
                            .email(email)
                            .name(name)
                            .gender(memberGender)
                            .address("소셜로그인")
                            .specAddress("소셜로그인")
                            .role(Role.USER)
                            .socialType(socialType)
                            .providerId(providerId)
                            .loginId(loginId)
                            .status(MemberStatus.ACTIVE)
                            .build();
                    newMember.generateOAuth2Password();
                    return newMember;
                });

        return memberRepository.save(member);
    }
}
