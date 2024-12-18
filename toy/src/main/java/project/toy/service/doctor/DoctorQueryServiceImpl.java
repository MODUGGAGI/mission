package project.toy.service.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.DepartmentHandler;
import project.toy.apiPayload.exception.handler.DoctorHandler;
import project.toy.converter.DoctorConverter;
import project.toy.domain.Department;
import project.toy.domain.Doctor;
import project.toy.repository.DepartmentRepository;
import project.toy.repository.DoctorRepository;
import project.toy.web.dto.doctor.DoctorResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoctorQueryServiceImpl implements DoctorQueryService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public DoctorResponseDto.DoctorDTO getDoctor(Long hospitalId, Long departmentId, Long doctorId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_FOUND));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorHandler(ErrorStatus.DOCTOR_NOT_FOUND));

        if (!department.getHospital().getId().equals(hospitalId)) {
            throw new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_EXISTS);
        }

        if (!doctor.getDepartment().equals(department)) {
            throw new DoctorHandler(ErrorStatus.DOCTOR_NOT_EXISTS);
        }

        return DoctorConverter.toDoctorDTO(doctor);
    }

    @Override
    public DoctorResponseDto.DoctorListDTO getDoctors(Long hospitalId, Long departmentId, Integer page) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_FOUND));

        if (!department.getHospital().getId().equals(hospitalId)) {
            throw new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_EXISTS);
        }

        return DoctorConverter.toDoctorListDTO(doctorRepository.findAllByDepartment(department, PageRequest.of(page, 10)));
    }
}
