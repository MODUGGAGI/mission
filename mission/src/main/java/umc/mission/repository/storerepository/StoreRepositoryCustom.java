package umc.mission.repository.storerepository;

import umc.mission.domain.Store;
import umc.mission.domain.enums.MissionStatus;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}