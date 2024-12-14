package project.toy.service.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.DepartmentHandler;
import project.toy.apiPayload.exception.handler.HospitalHandler;
import project.toy.converter.DoctorConverter;
import project.toy.domain.Department;
import project.toy.domain.Doctor;
import project.toy.domain.Hospital;
import project.toy.repository.DepartmentRepository;
import project.toy.repository.DoctorRepository;
import project.toy.repository.HospitalRepository;
import project.toy.web.dto.doctor.DoctorRequestDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoctorCommandServiceImpl implements DoctorCommandService{

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public Doctor joinDoctor(DoctorRequestDto.DoctorJoinDTO request, Long hospitalId, Long departmentId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalHandler(ErrorStatus.HOSPITAL_NOT_FOUND));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_FOUND));

        Doctor doctor = DoctorConverter.toDoctor(request, department);

        if (hospital.getDepartments().contains(department)) {
            return doctorRepository.save(doctor);
        }

        throw new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_EXISTS);
    }
}
