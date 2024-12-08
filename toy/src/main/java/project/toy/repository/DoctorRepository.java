package project.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Department;
import project.toy.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
