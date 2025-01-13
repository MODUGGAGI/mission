package umc.mission.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import umc.mission.domain.Member;
import umc.mission.domain.enums.MemberStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member member; // User -> Member로 변경
    private Map<String, Object> attributes;

    // 일반 로그인 시 사용되는 생성자
    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // OAuth2 로그인 시 사용되는 생성자
    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    /**
     * OAuth2User 구현
     */
    @Override
    public String getName() {
        return member.getName();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * UserDetails 구현
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_" + member.getRole().name());
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();  // email을 username으로 사용
    }

    @Override
    public boolean isEnabled() {
        return member.getStatus() == MemberStatus.ACTIVE;  // ACTIVE 상태일 때만 사용 가능
    }
}
