package umc.mission.service.membermissionservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.mission.domain.enums.MissionStatus;
import umc.mission.repository.membermissionrepository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionServiceImpl implements MemberMissionService{

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean alreadyChallenging(Long memberId, Long missionId) {
        return memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(memberId, missionId, MissionStatus.CHALLENGING);
    }
}
