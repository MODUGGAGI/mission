package project.toy.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import project.toy.service.department.DepartmentQueryService;
import project.toy.service.doctor.DoctorCommandService;
import project.toy.service.doctor.DoctorQueryService;
import project.toy.service.hospital.HospitalCommandService;
import project.toy.service.hospital.HospitalQueryService;
import project.toy.validation.annotation.CheckPage;
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
    private final HospitalQueryService hospitalQueryService;
    private final DepartmentQueryService departmentQueryService;
    private final DoctorQueryService doctorQueryService;

    @PostMapping("/")
    @Operation(summary = "병원 등록 API", description = "병원을 등록하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    public ApiResponse<HospitalResponseDto.HospitalJoinResultDTO> join(@RequestBody @Valid HospitalRequestDto.HospitalJoinDTO request) {
        Hospital hospital = hospitalCommandService.joinHospital(request);

        return ApiResponse.onSuccess(HospitalConverter.toHospitalJoinResultDto(hospital));
    }

    @GetMapping("/")
    @Operation(summary = "병원 조회하는 API", description = "등록된 병원들을 모두 조회하는 API, 페이징 적용")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    )
    @Parameters(
            @Parameter(name = "page", description = "조회할 page 번호입니다.")
    )
    public ApiResponse<HospitalResponseDto.HospitalListDTO> getHospitalList(@CheckPage Integer page) {
        Page<Hospital> hospitalList = hospitalQueryService.getHospital(page);
        return ApiResponse.onSuccess(HospitalConverter.toHospitalListDTO(hospitalList));
    }

    @PostMapping("/{hospitalId}/departments")
    @Operation(summary = "병원에 진료과 등록 API", description = "병원에 진료과를 등록하는 API")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    )
    @Parameters(
            @Parameter(name = "hospitalId", description = "병원의 ID, path variable입니다.")
    )
    public ApiResponse<DepartmentResponseDto.DepartmentJoinResultDTO> joinDepartment(
            @RequestBody @Valid DepartmentRequestDto.DepartmentJoinDTO request,
            @PathVariable Long hospitalId
    ) {
        Department department = departmentCommandService.joinDepartment(request, hospitalId);

        return ApiResponse.onSuccess(DepartmentConverter.toDepartmentJoinResultDTO(department));
    }

    @GetMapping("/{hospitalId}/departments")
    @Operation(summary = "병원에 등록된 진료과를 조회하는 API", description = "병원에 등록된 진료과를 조회하는 API이며 페이징을 적용")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공")
    )
    @Parameters({
            @Parameter(name = "hospitalId", description = "병원의 ID, path variable입니다."),
            @Parameter(name = "page", description = "조회할 page 번호입니다.")
    })
    public ApiResponse<DepartmentResponseDto.DepartmentListDTO> getDepartments(@PathVariable Long hospitalId, @CheckPage Integer page) {
        Page<Department> departments = departmentQueryService.getDepartments(hospitalId, page);

        return ApiResponse.onSuccess(DepartmentConverter.toDepartmentListDTO(departments));
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

    @GetMapping("/{hospitalId}/departments/{departmentId}/doctors")
    @Operation(summary = "진료과의 의사 목록을 조회하는 API", description = "특정 진료과에 소속된 의사들을 조회하는 API이며 페이징을 적용")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공")
    )
    @Parameters({
            @Parameter(name = "hospitalId", description = "병원의 ID, path variable입니다."),
            @Parameter(name = "departmentId", description = "진료과의 ID, path variable입니다."),
            @Parameter(name = "page", description = "조회할 page 번호입니다.")
    })
    public ApiResponse<DoctorResponseDto.DoctorListDTO> getDoctors(
            @PathVariable Long hospitalId,
            @PathVariable Long departmentId,
            @CheckPage Integer page
    ) {
        Page<Doctor> doctors = doctorQueryService.getDoctors(hospitalId, departmentId, page);
        return ApiResponse.onSuccess(DoctorConverter.toDoctorListDTO(doctors));
    }
}
