package project.toy.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.toy.converter.PatientConverter;
import project.toy.domain.Patient;
import project.toy.service.patient.PatientCommandService;
import project.toy.web.dto.patient.PatientRequestDto;
import project.toy.web.dto.patient.PatientResponseDto;
import project.toy.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientRestController {

    private final PatientCommandService patientCommandService;

    @PostMapping("/")
    @Operation(summary = "환자 등록 API", description = "환자의 정보를 등록하게 되는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    public project.toy.apiPayload.ApiResponse<PatientResponseDto.PatientJoinResultDTO> join(@RequestBody @Valid PatientRequestDto.PatientJoinDTO request) {

        Patient patient = patientCommandService.joinPatient(request);

        return ApiResponse.onSuccess(PatientConverter.toPatientJoinResultDto(patient));
    }
}
