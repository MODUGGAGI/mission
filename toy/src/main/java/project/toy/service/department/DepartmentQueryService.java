package project.toy.service.department;

import org.springframework.data.domain.Page;
import project.toy.domain.Department;

public interface DepartmentQueryService {
    Page<Department> getDepartments(Long hospitalId, Integer page);
}
