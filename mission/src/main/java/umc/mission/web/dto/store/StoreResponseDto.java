package umc.mission.web.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.mission.web.dto.region.RegionResponseDto;

import java.time.LocalDate;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
        String ownerNickname;
        Float score;
        String body;
        LocalDate createdAt;
    }
}
