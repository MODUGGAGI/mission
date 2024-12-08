package project.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
