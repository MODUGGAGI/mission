package project.toy.service.department;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.DepartmentHandler;
import project.toy.apiPayload.exception.handler.HospitalHandler;
import project.toy.converter.DepartmentConverter;
import project.toy.domain.Department;
import project.toy.domain.Hospital;
import project.toy.repository.DepartmentRepository;
import project.toy.repository.HospitalRepository;
import project.toy.web.dto.department.DepartmentResponseDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentQueryServiceImpl implements DepartmentQueryService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public DepartmentResponseDto.DepartmentDTO getDepartment(Long hospitalId, Long departmentId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalHandler(ErrorStatus.HOSPITAL_NOT_FOUND));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_FOUND));

        if (!hospital.getDepartments().contains(department)) {
            throw new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_EXISTS);
        }

        return DepartmentConverter.toDepartmentDTO(department);
    }

    @Override
    public DepartmentResponseDto.DepartmentListDTO getDepartments(Long hospitalId, Integer page) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalHandler(ErrorStatus.HOSPITAL_NOT_FOUND));

        return DepartmentConverter.toDepartmentListDTO(departmentRepository.findAllByHospital(hospital, PageRequest.of(page, 10)));
    }
}
