package project.toy.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.toy.apiPayload.ApiResponse;
import project.toy.converter.ReserveConverter;
import project.toy.domain.Reserve;
import project.toy.service.reserve.ReserveCommandService;
import project.toy.service.reserve.ReserveQueryService;
import project.toy.validation.annotation.CheckPage;
import project.toy.web.dto.reserve.ReserveRequestDto;
import project.toy.web.dto.reserve.ReserveResponseDto;

@RestController
@RequestMapping("/reserves")
@RequiredArgsConstructor
public class ReserveRestController {

    private final ReserveCommandService reserveCommandService;
    private final ReserveQueryService reserveQueryService;

    @PostMapping("/")
    @Operation(summary = "예약 정보 등록 API", description = "예약을 등록하는 API, 환자 정보와 의사 정보는 RequestBody로 받는다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    public ApiResponse<ReserveResponseDto.ReserveJoinResultDTO> joinReserve(@RequestBody @Valid ReserveRequestDto.ReserveJoinDTO request) {

        return ApiResponse.onSuccess(reserveCommandService.joinReserve(request));
    }

    @PatchMapping("/{reserveId}/treatment")
    @Operation(summary = "진료 완료 처리 API", description = "예약 상태를 진료 완료로 변경하고 진료비를 부과하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "RESERVE4004", description = "이미 진료가 끝난 상태입니다.")
    })
    @Parameters({
            @Parameter(name = "reserveId", description = "예약 ID"),
    })
    public ApiResponse<ReserveResponseDto.TreatmentResultDTO> completeTreatment(
            @PathVariable Long reserveId,
            @RequestBody @Valid ReserveRequestDto.TreatmentDTO request
    ) {

        return ApiResponse.onSuccess(reserveCommandService.completeTreatment(reserveId, request.getPrice()));
    }

    @GetMapping("/patients/{patientId}/reserves")
    @Operation(summary = "환자의 예약 내역 조회 API",
            description = "환자의 예약 내역을 조회하는 API (1: 진행중, 2: 완료)")
    @Parameters({
            @Parameter(name = "patientId", description = "환자 ID"),
            @Parameter(name = "statusFilter", description = "상태 필터 (1: 진행중, 2: 완료), Query String이며 필수값 아님"),
            @Parameter(name = "doctorId", description = "특정 의사와의 진료 기록 검색, Query String이며 필수값 아님"),
            @Parameter(name = "page", description = "페이지 번호, Query String")
    })
    public ApiResponse<ReserveResponseDto.ReserveListDTO> getPatientReserves(
            @PathVariable Long patientId,
            @RequestParam(required = false) Integer statusFilter,
            @RequestParam(required = false) Long doctorId,
            @CheckPage Integer page
    ) {
        ReserveResponseDto.ReserveListDTO result =
                reserveQueryService.getPatientReserves(patientId, statusFilter, doctorId, page);

        return ApiResponse.onSuccess(result);
    }


    @GetMapping("/hospitals/{hospitalId}/departments/{departmentId}/doctors/{doctorId}/reserves")
    @Operation(summary = "의사의 예약 목록 조회 API",
            description = "의사의 예약 목록을 조회하는 API (1: 진행중, 2: 완료)")
    @Parameters({
            @Parameter(name = "hospitalId", description = "병원 ID"),
            @Parameter(name = "departmentId", description = "진료과 ID"),
            @Parameter(name = "doctorId", description = "의사 ID"),
            @Parameter(name = "statusFilter", description = "상태 필터 (1: 진행중, 2: 완료), Query String이며 필수값 아님"),
            @Parameter(name = "patientId", description = "특정 환자와의 진료 기록 검색, Query String이며 필수값 아님"),
            @Parameter(name = "page", description = "페이지 번호, Query String")
    })
    public ApiResponse<ReserveResponseDto.ReserveListDTO> getDoctorReserves(
            @PathVariable Long hospitalId,
            @PathVariable Long departmentId,
            @PathVariable Long doctorId,
            @RequestParam(required = false) Integer statusFilter,
            @RequestParam(required = false) Long patientId,
            @CheckPage Integer page
    ) {

        ReserveResponseDto.ReserveListDTO result
                = reserveQueryService.getDoctorReserves(hospitalId, departmentId, doctorId, statusFilter, patientId, page);

        return ApiResponse.onSuccess(result);
    }
}
