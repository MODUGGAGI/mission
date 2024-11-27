package umc.mission.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.mission.apiPayload.ApiResponse;
import umc.mission.converter.StoreConverter;
import umc.mission.domain.Store;
import umc.mission.service.storeservice.StoreCommandService;
import umc.mission.web.dto.StoreRequestDto;
import umc.mission.web.dto.StoreResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    @PostMapping("/")
    public ApiResponse<StoreResponseDto.StoreJoinResultDto> join(@RequestBody StoreRequestDto.StoreJoinDto request) {
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDto(store));
    }
}
