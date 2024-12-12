package project.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Reserve;

import java.time.LocalDateTime;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    boolean existsByDoctorIdAndTreatmentTime(Long doctorId, LocalDateTime treatmentTime);
}
