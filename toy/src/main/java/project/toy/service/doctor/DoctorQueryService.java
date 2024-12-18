package project.toy.service.doctor;

import org.springframework.data.domain.Page;
import project.toy.domain.Doctor;
import project.toy.web.dto.doctor.DoctorResponseDto;

public interface DoctorQueryService {
    DoctorResponseDto.DoctorDTO getDoctor(Long hospitalId, Long departmentId, Long doctorId);
    DoctorResponseDto.DoctorListDTO getDoctors(Long hospitalId, Long departmentId, Integer page);
}
