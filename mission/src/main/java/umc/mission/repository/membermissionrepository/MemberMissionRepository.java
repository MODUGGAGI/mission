package umc.mission.repository.membermissionrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.domain.enums.MissionStatus;
import umc.mission.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);
}