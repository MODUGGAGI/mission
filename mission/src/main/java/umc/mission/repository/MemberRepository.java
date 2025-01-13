package umc.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.mission.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    <T> Optional<T> findByLoginId(String loginId);

    // 추가: 이메일 존재 여부 확인 (소셜 로그인 포함 전체 확인)
    boolean existsByEmail(String email);
}