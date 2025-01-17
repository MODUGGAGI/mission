package umc.mission.converter;

import org.springframework.data.domain.Page;
import umc.mission.domain.Mission;
import umc.mission.domain.Region;
import umc.mission.domain.Review;
import umc.mission.domain.Store;
import umc.mission.web.dto.region.RegionResponseDto;
import umc.mission.web.dto.store.StoreRequestDto;
import umc.mission.web.dto.store.StoreResponseDto;

import java.util.List;

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

    public static StoreResponseDto.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return StoreResponseDto.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static StoreResponseDto.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList) {

        List<StoreResponseDto.ReviewPreViewDTO> reviewPreViewDTOList
                = reviewList.stream().map(StoreConverter::reviewPreViewDTO).toList();

        return StoreResponseDto.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDto.MissionDTO toMissionDTO(Mission mission) {
        return StoreResponseDto.MissionDTO.builder()
                .storeName(mission.getStore().getName())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    public static StoreResponseDto.MissionListDTO toMissionListDTO(Page<Mission> missionList) {
        List<StoreResponseDto.MissionDTO> missionDTOList
                = missionList.stream().map(StoreConverter::toMissionDTO).toList();

        return StoreResponseDto.MissionListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionDTOList.size())
                .missionList(missionDTOList)
                .build();
    }
}
