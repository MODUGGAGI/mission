package project.toy.service.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.DepartmentHandler;
import project.toy.domain.Department;
import project.toy.domain.Doctor;
import project.toy.repository.DepartmentRepository;
import project.toy.repository.DoctorRepository;

@Service
@RequiredArgsConstructor
public class DoctorQueryServiceImpl implements DoctorQueryService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Page<Doctor> getDoctors(Long hospitalId, Long departmentId, Integer page) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_FOUND));

        if (!department.getHospital().getId().equals(hospitalId)) {
            throw new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_EXISTS);
        }

        return doctorRepository.findAllByDepartment(department, PageRequest.of(page, 10));
    }
}
