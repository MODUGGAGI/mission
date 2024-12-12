package project.toy.converter;

import project.toy.domain.Department;
import project.toy.domain.embeddable.PhoneNum;
import project.toy.web.dto.department.DepartmentRequestDto;
import project.toy.web.dto.department.DepartmentResponseDto;

public class DepartmentConverter {

    public static Department toDepartment(DepartmentRequestDto.DepartmentJoinDTO request) {
        return Department.builder()
                .name(request.getName())
                .phoneNum(PhoneNum.builder().phoneNum(request.getPhoneNum()).build())
                .build();
    }

    public static DepartmentResponseDto.DepartmentJoinResultDTO toDepartmentJoinResultDTO(Department department) {
        return DepartmentResponseDto.DepartmentJoinResultDTO.builder()
                .id(department.getId())
                .hospitalName(department.getHospital().getName())
                .departmentName(department.getName())
                .build();
    }
}
