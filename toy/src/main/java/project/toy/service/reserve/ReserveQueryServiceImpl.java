package project.toy.service.reserve;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.DepartmentHandler;
import project.toy.apiPayload.exception.handler.DoctorHandler;
import project.toy.apiPayload.exception.handler.HospitalHandler;
import project.toy.apiPayload.exception.handler.PatientHandler;
import project.toy.converter.ReserveConverter;
import project.toy.domain.*;
import project.toy.domain.enums.ReserveStatus;
import project.toy.repository.DepartmentRepository;
import project.toy.repository.DoctorRepository;
import project.toy.repository.HospitalRepository;
import project.toy.repository.PatientRepository;
import project.toy.repository.reserve.ReserveRepository;
import project.toy.web.dto.reserve.ReserveResponseDto;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReserveQueryServiceImpl implements ReserveQueryService{

    private final ReserveRepository reserveRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public ReserveResponseDto.ReserveListDTO getPatientReserves(Long patientId, Integer statusFilter, Integer page) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientHandler(ErrorStatus.PATIENT_NOT_FOUND));

        Page<Reserve> reserves = reserveRepository.findAllByPatientWithStatus(
                patient,
                statusFilter,
                PageRequest.of(page, 10)
        );

        return ReserveConverter.toReserveListDTO(reserves);
    }

    @Override
    public ReserveResponseDto.ReserveListDTO getDoctorReserves(
            Long hospitalId, Long departmentId, Long doctorId, Integer statusFilter, Integer page) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalHandler(ErrorStatus.HOSPITAL_NOT_FOUND));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_FOUND));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorHandler(ErrorStatus.DOCTOR_NOT_FOUND));

        // 진료과가 해당 병원에 소속되어 있는지 확인
        if (!doctor.getDepartment().getHospital().equals(hospital)) {
            throw new DepartmentHandler(ErrorStatus.DEPARTMENT_NOT_EXISTS);
        }

        // 의사가 해당 진료과에 소속되어 있는지 확인
        if (!doctor.getDepartment().equals(department)) {
            throw new DoctorHandler(ErrorStatus.DOCTOR_NOT_FOUND);
        }

        Page<Reserve> reserves = reserveRepository.findAllByDoctorWithStatus(
                doctor,
                statusFilter,
                PageRequest.of(page, 10)
        );

        return ReserveConverter.toReserveListDTO(reserves);
    }
}
