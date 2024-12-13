package project.toy.converter;

import org.springframework.data.domain.Page;
import project.toy.domain.Department;
import project.toy.domain.Hospital;
import project.toy.domain.embeddable.PhoneNum;
import project.toy.web.dto.department.DepartmentRequestDto;
import project.toy.web.dto.department.DepartmentResponseDto;

import java.util.List;

public class DepartmentConverter {

    public static Department toDepartment(DepartmentRequestDto.DepartmentJoinDTO request, Hospital hospital) {
        return Department.builder()
                .name(request.getName())
                .phoneNum(PhoneNum.builder().phoneNum(request.getPhoneNum()).build())
                .hospital(hospital)
                .build();
    }

    public static DepartmentResponseDto.DepartmentJoinResultDTO toDepartmentJoinResultDTO(Department department) {
        return DepartmentResponseDto.DepartmentJoinResultDTO.builder()
                .id(department.getId())
                .hospitalName(department.getHospital().getName())
                .departmentName(department.getName())
                .build();
    }

    public static DepartmentResponseDto.DepartmentListDTO toDepartmentListDTO(Page<Department> departmentList) {
        List<DepartmentResponseDto.DepartmentDTO> list
                = departmentList.stream().map(DepartmentConverter::toDepartmentDTO).toList();

        return DepartmentResponseDto.DepartmentListDTO.builder()
                .departmentDTOList(list)
                .listSize(list.size())
                .isFirst(departmentList.isFirst())
                .isLast(departmentList.isLast())
                .totalElement(departmentList.getTotalElements())
                .totalPage(departmentList.getTotalPages())
                .build();
    }

    public static DepartmentResponseDto.DepartmentDTO toDepartmentDTO(Department department) {
        return DepartmentResponseDto.DepartmentDTO.builder()
                .departmentName(department.getName())
                .phoneNum(department.getPhoneNum().getPhoneNum())
                .build();
    }
}
