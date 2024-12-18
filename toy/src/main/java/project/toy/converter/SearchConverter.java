package project.toy.converter;

import org.springframework.data.domain.Page;
import project.toy.domain.Department;
import project.toy.domain.Doctor;
import project.toy.domain.Hospital;
import project.toy.web.dto.department.DepartmentResponseDto;
import project.toy.web.dto.doctor.DoctorResponseDto;
import project.toy.web.dto.hospital.HospitalResponseDto;
import project.toy.web.dto.search.SearchResponseDto;

public class SearchConverter {

    public static SearchResponseDto.HospitalSearchResultDTO toSearchResult(
            Page<Hospital> hospitals,
            Page<Department> departments,
            Page<Doctor> doctors
    ) {
        HospitalResponseDto.HospitalListDTO hospitalListDTO = HospitalConverter.toHospitalListDTO(hospitals);
        DepartmentResponseDto.DepartmentListDTO departmentListDTO = DepartmentConverter.toDepartmentListDTO(departments);
        DoctorResponseDto.DoctorListDTO doctorListDTO = DoctorConverter.toDoctorListDTO(doctors);

        return SearchResponseDto.HospitalSearchResultDTO.builder()
                .hospitalListDTO(hospitalListDTO)
                .departmentListDTO(departmentListDTO)
                .doctorListDTO(doctorListDTO)
                .build();
    }
}
