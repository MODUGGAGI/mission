package umc.mission.service.storeservice;

import umc.mission.domain.Store;
import umc.mission.web.dto.store.StoreRequestDto;

public interface StoreCommandService {
    Store joinStore(StoreRequestDto.StoreJoinDto request);
}
