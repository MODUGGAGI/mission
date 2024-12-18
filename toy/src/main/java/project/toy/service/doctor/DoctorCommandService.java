package project.toy.service.doctor;

import project.toy.domain.Doctor;
import project.toy.web.dto.doctor.DoctorRequestDto;
import project.toy.web.dto.doctor.DoctorResponseDto;

public interface DoctorCommandService {
    DoctorResponseDto.DoctorJoinResultDTO joinDoctor(DoctorRequestDto.DoctorJoinDTO request, Long hospitalId, Long departmentId);
}
