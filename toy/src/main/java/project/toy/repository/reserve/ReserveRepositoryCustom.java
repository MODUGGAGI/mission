package project.toy.repository.reserve;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import project.toy.domain.Doctor;
import project.toy.domain.Patient;
import project.toy.domain.Reserve;

public interface ReserveRepositoryCustom {
    Page<Reserve> findAllByPatientWithStatus(Patient patient, Integer statusFilter, PageRequest pageRequest);
    Page<Reserve> findAllByDoctorWithStatus(Doctor doctor, Integer statusFilter, PageRequest pageRequest);
}
