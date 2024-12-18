package project.toy.service.department;

import project.toy.domain.Department;
import project.toy.web.dto.department.DepartmentRequestDto;
import project.toy.web.dto.department.DepartmentResponseDto;

public interface DepartmentCommandService {

    DepartmentResponseDto.DepartmentJoinResultDTO joinDepartment(DepartmentRequestDto.DepartmentJoinDTO request, Long hospitalId);
}
