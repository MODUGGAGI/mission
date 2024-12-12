package project.toy.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.toy.apiPayload.ApiResponse;
import project.toy.converter.DepartmentConverter;
import project.toy.converter.DoctorConverter;
import project.toy.converter.HospitalConverter;
import project.toy.domain.Department;
import project.toy.domain.Doctor;
import project.toy.domain.Hospital;
import project.toy.service.department.DepartmentCommandService;
import project.toy.service.doctor.DoctorCommandService;
import project.toy.service.hospital.HospitalCommandService;
import project.toy.web.dto.department.DepartmentRequestDto;
import project.toy.web.dto.department.DepartmentResponseDto;
import project.toy.web.dto.doctor.DoctorRequestDto;
import project.toy.web.dto.doctor.DoctorResponseDto;
import project.toy.web.dto.hospital.HospitalRequestDto;
import project.toy.web.dto.hospital.HospitalResponseDto;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalRestController {

    private final HospitalCommandService hospitalCommandService;
    private final DepartmentCommandService departmentCommandService;
    private final DoctorCommandService doctorCommandService;

    @PostMapping("/")
    @Operation(summary = "병원 등록 API", description = "병원을 등록하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    public ApiResponse<HospitalResponseDto.HospitalJoinResultDTO> join(@RequestBody @Valid HospitalRequestDto.HospitalJoinDTO request) {
        Hospital hospital = hospitalCommandService.joinHospital(request);

        return ApiResponse.onSuccess(HospitalConverter.toHospitalJoinResultDto(hospital));
    }

    @PostMapping("/{hospitalId}/departments")
    @Operation(summary = "병원에 진료과 등록 API", description = "병원에 진료과를 등록하는 API")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    )
    @Parameters(
            @Parameter(name = "hospitalId",description = "병원의 ID, path variable입니다.")
    )
    public ApiResponse<DepartmentResponseDto.DepartmentJoinResultDTO> joinDepartment(
            @RequestBody @Valid DepartmentRequestDto.DepartmentJoinDTO request,
            @PathVariable Long hospitalId
    ) {
        Department department = departmentCommandService.joinDepartment(request, hospitalId);

        return ApiResponse.onSuccess(DepartmentConverter.toDepartmentJoinResultDTO(department));
    }

    @PostMapping("/{hospitalId}/departments/{departmentId}/doctors")
    @Operation(summary = "병원의 진료과에 의사 등록 API", description = "병원의 진료과에 의사 등록하는 API")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    )
    @Parameters({
            @Parameter(name = "hospitalId", description = "병원의 ID, path variable입니다."),
            @Parameter(name = "departmentId", description = "진료과의 ID, path variable입니다.")
    })
    public ApiResponse<DoctorResponseDto.DoctorJoinResultDTO> joinDoctor(
            @RequestBody @Valid DoctorRequestDto.DoctorJoinDTO request,
            @PathVariable Long hospitalId,
            @PathVariable Long departmentId
    ) {
        Doctor doctor = doctorCommandService.joinDoctor(request, hospitalId, departmentId);

        return ApiResponse.onSuccess(DoctorConverter.toDoctorJoinResultDTO(doctor));
    }
}
