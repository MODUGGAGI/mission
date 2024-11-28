package umc.mission.converter;

import umc.mission.domain.Region;
import umc.mission.domain.Store;
import umc.mission.web.dto.region.RegionResponseDto;
import umc.mission.web.dto.store.StoreRequestDto;
import umc.mission.web.dto.store.StoreResponseDto;

public class StoreConverter {

    public static StoreResponseDto.StoreJoinResultDto toJoinResultDto(Store store) {
        return StoreResponseDto.StoreJoinResultDto.builder()
                .storeId(store.getId())
                .region(RegionResponseDto.RegionDto.builder()
                        .regionId(store.getRegion().getId())
                        .name(store.getRegion().getName())
                        .build())
                .build();
    }

    public static Store toStore(StoreRequestDto.StoreJoinDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }

}
