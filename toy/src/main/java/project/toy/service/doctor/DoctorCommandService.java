package project.toy.service.doctor;

import project.toy.domain.Doctor;
import project.toy.web.dto.doctor.DoctorRequestDto;

public interface DoctorCommandService {
    public Doctor joinDoctor(DoctorRequestDto.DoctorJoinDTO request, Long hospitalId, Long departmentId);
}
