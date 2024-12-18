package project.toy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Doctor;
import project.toy.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByNameContaining(String name, Pageable pageable);
}
