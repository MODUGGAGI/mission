package umc.mission.web.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.mission.web.dto.region.RegionResponseDto;

public class StoreResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreJoinResultDto {
        Long storeId;
        RegionResponseDto.RegionDto region;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreDto {
        Long storeId;
        String name;
    }
}
