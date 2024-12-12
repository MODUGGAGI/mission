package project.toy.repository.reserve;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Patient;
import project.toy.domain.Reserve;
import project.toy.domain.enums.ReserveStatus;

import java.time.LocalDateTime;

public interface ReserveRepository extends JpaRepository<Reserve, Long>, ReserveRepositoryCustom {
    boolean existsByDoctorIdAndTreatmentTime(Long doctorId, LocalDateTime treatmentTime);

    Page<Reserve> findAllByPatient(Patient patient, PageRequest pageRequest);

    Page<Reserve> findAllByPatientAndStatus(Patient patient, ReserveStatus status, PageRequest pageRequest);
}
