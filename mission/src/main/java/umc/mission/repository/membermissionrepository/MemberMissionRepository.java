package umc.mission.repository.membermissionrepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.domain.enums.MissionStatus;
import umc.mission.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    Page<MemberMission> findAllByMemberAndStatus(Member member, PageRequest pageRequest, MissionStatus status);

    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);
}
