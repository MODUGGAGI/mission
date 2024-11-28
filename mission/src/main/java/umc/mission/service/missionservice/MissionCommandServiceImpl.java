package umc.mission.service.missionservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mission.apiPayload.code.status.ErrorStatus;
import umc.mission.apiPayload.exception.handler.StoreHandler;
import umc.mission.converter.MissionConverter;
import umc.mission.domain.Mission;
import umc.mission.domain.Store;
import umc.mission.repository.MissionRepository;
import umc.mission.repository.storerepository.StoreRepository;
import umc.mission.web.dto.mission.MissionRequestDto;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission joinMission(MissionRequestDto.MissionJoinDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request, store);

        mission.setStore(store);

        return missionRepository.save(mission);
    }
}
