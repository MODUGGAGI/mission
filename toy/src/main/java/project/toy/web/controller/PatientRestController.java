package project.toy.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.toy.service.patient.PatientCommandService;
import project.toy.service.patient.PatientQueryService;
import project.toy.validation.annotation.CheckPage;
import project.toy.web.dto.patient.PatientRequestDto;
import project.toy.web.dto.patient.PatientResponseDto;
import project.toy.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientRestController {

    private final PatientCommandService patientCommandService;
    private final PatientQueryService patientQueryService;

    @PostMapping("/")
    @Operation(summary = "환자 등록 API", description = "환자의 정보를 등록하게 되는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    public ApiResponse<PatientResponseDto.PatientJoinResultDTO> join(@RequestBody @Valid PatientRequestDto.PatientJoinDTO request) {

        return ApiResponse.onSuccess(patientCommandService.joinPatient(request));
    }

    @GetMapping("/")
    @Operation(summary = "환자 조회하는 API", description = "등록된 모든 환자들을 조회하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공")
    })
    @Parameters(
            @Parameter(name = "page", description = "페이지 번호, Query string")
    )
    public ApiResponse<PatientResponseDto.PatientListDTO> getPatients(@CheckPage Integer page) {

        return ApiResponse.onSuccess(patientQueryService.getPatients(page));
    }

    @GetMapping("/{patientId}")
    @Operation(summary = "특정 환자 id값으로 조회하는 API", description = "특정 환자 id값으로 조회하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공")
    })
    @Parameters(
            @Parameter(name = "patientId", description = "환자의 Id값, path variable")
    )
    public ApiResponse<PatientResponseDto.PatientDTO> getPatient(@PathVariable Long patientId) {

        return ApiResponse.onSuccess(patientQueryService.getPatient(patientId));
    }

    @GetMapping("/search")
    @Operation(summary = "환자를 이름으로 조회하는 API", description = "환자를 이름으로 검색하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "name", description = "환자의 이름, query string"),
            @Parameter(name = "page", description = "페이지 번호, Query string")
    })
    public ApiResponse<PatientResponseDto.PatientListDTO> getPatients(
            @RequestParam String name,
            @CheckPage Integer page
    ) {
        return ApiResponse.onSuccess(patientQueryService.getPatientByName(name, page));
    }
}
