package project.toy.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.toy.apiPayload.ApiResponse;
import project.toy.service.search.SearchQueryService;
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
import project.toy.web.dto.search.SearchResponseDto;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalRestController {

    private final HospitalCommandService hospitalCommandService;
    private final HospitalQueryService hospitalQueryService;
    private final DepartmentCommandService departmentCommandService;
    private final DepartmentQueryService departmentQueryService;
    private final DoctorCommandService doctorCommandService;
    private final DoctorQueryService doctorQueryService;
    private final SearchQueryService searchQueryService;

    @PostMapping("/")
    @Operation(summary = "병원 등록 API", description = "병원을 등록하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    public ApiResponse<HospitalResponseDto.HospitalJoinResultDTO> join(@RequestBody @Valid HospitalRequestDto.HospitalJoinDTO request) {

        return ApiResponse.onSuccess(hospitalCommandService.joinHospital(request));
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

        return ApiResponse.onSuccess(hospitalQueryService.getHospitals(page));
    }

    @GetMapping("/{hospitalId}")
    @Operation(summary = "특정 병원 id값으로 조회 API", description = "특정 병원 id값으로 조회하는 API")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    )
    @Parameters(
            @Parameter(name = "hospitalId", description = "병원의 ID, path variable입니다.")
    )
    public ApiResponse<HospitalResponseDto.HospitalDTO> getHospital(@PathVariable Long hospitalId) {

        return ApiResponse.onSuccess(hospitalQueryService.getHospital(hospitalId));
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

        return ApiResponse.onSuccess(departmentCommandService.joinDepartment(request, hospitalId));
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

        return ApiResponse.onSuccess(departmentQueryService.getDepartments(hospitalId, page));
    }

    @GetMapping("/{hospitalId}/departments/{departmentId}")
    @Operation(summary = "특정 진료과 id값으로 조회 API", description = "특정 진료과 id값으로 조회하는 API")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    )
    @Parameters({
            @Parameter(name = "hospitalId", description = "병원의 ID, path variable입니다."),
            @Parameter(name = "departmentId", description = "진료과의 ID, path variable입니다.")
    })
    public ApiResponse<DepartmentResponseDto.DepartmentDTO> getDepartment(@PathVariable Long hospitalId, @PathVariable Long departmentId) {

        return ApiResponse.onSuccess(departmentQueryService.getDepartment(hospitalId, departmentId));
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

        return ApiResponse.onSuccess(doctorCommandService.joinDoctor(request, hospitalId, departmentId));
    }

    @GetMapping("/{hospitalId}/departments/{departmentId}/doctors")
    @Operation(summary = "진료과의 의사 목록을 조회하는 API", description = "특정 진료과에 소속된 의사들을 조회하는 API이며 페이징을 적용")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공")
    )
    @Parameters({
            @Parameter(name = "hospitalId", description = "병원의 ID, path variable입니다."),
            @Parameter(name = "departmentId", description = "진료과의 ID, path variable입니다."),
            @Parameter(name = "page", description = "조회할 page 번호입니다. query string")
    })
    public ApiResponse<DoctorResponseDto.DoctorListDTO> getDoctors(
            @PathVariable Long hospitalId,
            @PathVariable Long departmentId,
            @CheckPage Integer page
    ) {

        return ApiResponse.onSuccess(doctorQueryService.getDoctors(hospitalId, departmentId, page));
    }

    @GetMapping("/{hospitalId}/departments/{departmentId}/doctors/{doctorId}")
    @Operation(summary = "특정 의사 id값으로 조회 API", description = "특정 의사 id값으로 조회하는 API")
    @ApiResponses(
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공")
    )
    @Parameters({
            @Parameter(name = "hospitalId", description = "병원의 ID, path variable입니다."),
            @Parameter(name = "departmentId", description = "진료과의 ID, path variable입니다."),
            @Parameter(name = "doctorId", description = "의사의 ID, path variable입니다.")
    })
    public ApiResponse<DoctorResponseDto.DoctorDTO> getDoctor(
            @PathVariable Long hospitalId,
            @PathVariable Long departmentId,
            @PathVariable Long doctorId
    ) {

        return ApiResponse.onSuccess(doctorQueryService.getDoctor(hospitalId, departmentId, doctorId));
    }

    @GetMapping("/search")
    @Operation(summary = "Hospital 내부 데이터 통합 검색 API", description = "병원, 진료과, 의사를 이름으로 검색, 페이징 적용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "name", description = "검색할 이름(병원, 진료과, 의사) query string"),
            @Parameter(name = "page", description = "조회할 page 번호입니다. query string")
    })
    public ApiResponse<SearchResponseDto.HospitalSearchResultDTO> getDoctorsByName(
            @RequestParam String name,
            @CheckPage Integer page
    ) {
        return ApiResponse.onSuccess(searchQueryService.getSearchResult(name, page));
    }
}
