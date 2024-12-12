package project.toy.service.department;

import project.toy.domain.Department;
import project.toy.web.dto.department.DepartmentRequestDto;

public interface DepartmentCommandService {

    public Department joinDepartment(DepartmentRequestDto.DepartmentJoinDTO request, Long hospitalId);
}
