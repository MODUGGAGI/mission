package umc.mission.service.storeservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mission.apiPayload.code.status.ErrorStatus;
import umc.mission.apiPayload.exception.handler.RegionHandler;
import umc.mission.converter.StoreConverter;
import umc.mission.domain.Region;
import umc.mission.domain.Store;
import umc.mission.repository.RegionRepository;
import umc.mission.repository.storerepository.StoreRepository;
import umc.mission.web.dto.StoreRequestDto;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    @Override
    @Transactional
    public Store joinStore(StoreRequestDto.JoinDto request) {

        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));
        Store newStore = StoreConverter.toStore(request, region);

        newStore.setRegion(region);

        return storeRepository.save(newStore);
    }
}
