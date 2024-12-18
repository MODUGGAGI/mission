package project.toy.service.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.HospitalHandler;
import project.toy.converter.DepartmentConverter;
import project.toy.domain.Department;
import project.toy.domain.Hospital;
import project.toy.repository.DepartmentRepository;
import project.toy.repository.HospitalRepository;
import project.toy.web.dto.department.DepartmentRequestDto;
import project.toy.web.dto.department.DepartmentResponseDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentCommandServiceImpl implements DepartmentCommandService{

    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public DepartmentResponseDto.DepartmentJoinResultDTO joinDepartment(DepartmentRequestDto.DepartmentJoinDTO request, Long hospitalId) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalHandler(ErrorStatus.HOSPITAL_NOT_FOUND));

        Department department = DepartmentConverter.toDepartment(request, hospital);

        return DepartmentConverter.toDepartmentJoinResultDTO(departmentRepository.save(department));
    }
}
