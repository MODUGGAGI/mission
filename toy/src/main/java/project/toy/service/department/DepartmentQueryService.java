package project.toy.service.department;

import org.springframework.data.domain.Page;
import project.toy.domain.Department;
import project.toy.web.dto.department.DepartmentResponseDto;

public interface DepartmentQueryService {

    DepartmentResponseDto.DepartmentDTO getDepartment(Long hospitalId, Long departmentId);
    DepartmentResponseDto.DepartmentListDTO getDepartments(Long hospitalId, Integer page);
}
