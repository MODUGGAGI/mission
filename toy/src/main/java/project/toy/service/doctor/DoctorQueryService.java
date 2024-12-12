package project.toy.service.doctor;

import org.springframework.data.domain.Page;
import project.toy.domain.Doctor;

public interface DoctorQueryService {
    Page<Doctor> getDoctors(Long hospitalId, Long departmentId, Integer page);
}
