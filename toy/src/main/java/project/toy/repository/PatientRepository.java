package project.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Doctor;
import project.toy.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
