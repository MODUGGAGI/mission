package project.toy.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.toy.apiPayload.ApiResponse;
import project.toy.converter.ReserveConverter;
import project.toy.domain.Reserve;
import project.toy.service.reserve.ReserveCommandService;
import project.toy.web.dto.reserve.ReserveRequestDto;
import project.toy.web.dto.reserve.ReserveResponseDto;

@RestController
@RequestMapping("/reserves")
@RequiredArgsConstructor
public class ReserveRestController {

    private final ReserveCommandService reserveCommandService;

    @PostMapping("/")
    @Operation(summary = "예약 정보 등록 API", description = "예약을 등록하는 API, 환자 정보와 의사 정보는 RequestBody로 받는다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    public ApiResponse<ReserveResponseDto.ReserveJoinResultDTO> joinReserve(@RequestBody @Valid ReserveRequestDto.ReserveJoinDTO request) {
        Reserve reserve = reserveCommandService.joinReserve(request);

        return ApiResponse.onSuccess(ReserveConverter.toReserveJoinResultDTO(reserve));
    }
}
