package umc.mission.converter;

import umc.mission.domain.Region;
import umc.mission.domain.Store;
import umc.mission.web.dto.StoreRequestDto;
import umc.mission.web.dto.StoreResponseDto;

public class StoreConverter {

    public static StoreResponseDto.JoinResultDto toJoinResultDto(Store store) {
        return StoreResponseDto.JoinResultDto.builder()
                .storeId(store.getId())
                .region(StoreResponseDto.RegionDto.builder()
                        .regionId(store.getRegion().getId())
                        .name(store.getRegion().getName())
                        .build())
                .build();
    }

    public static Store toStore(StoreRequestDto.JoinDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }

}
