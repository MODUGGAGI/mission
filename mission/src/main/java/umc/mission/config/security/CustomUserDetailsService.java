package umc.mission.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umc.mission.domain.Member;
import umc.mission.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService - loadUserByUsername 호출됨: {}", username);

        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> {
                    log.error("사용자를 찾을 수 없음: {}", username);
                    return new UsernameNotFoundException("해당 이메일을 가진 유저가 존재하지 않습니다: " + username);
                });

        log.info("사용자 찾음: {}, 권한: {}", member.getEmail(), member.getRole());
        return new PrincipalDetails(member);
    }
}
