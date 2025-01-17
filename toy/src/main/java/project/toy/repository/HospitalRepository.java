package project.toy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Page<Hospital> findAllByNameContaining(String name, Pageable pageable);
}
