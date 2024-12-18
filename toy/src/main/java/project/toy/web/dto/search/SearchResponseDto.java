package project.toy.web.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.toy.web.dto.department.DepartmentResponseDto;
import project.toy.web.dto.doctor.DoctorResponseDto;
import project.toy.web.dto.hospital.HospitalResponseDto;
import project.toy.web.dto.patient.PatientResponseDto;

public class SearchResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HospitalSearchResultDTO {
        HospitalResponseDto.HospitalListDTO hospitalListDTO;
        DepartmentResponseDto.DepartmentListDTO departmentListDTO;
        DoctorResponseDto.DoctorListDTO doctorListDTO;
    }
}
