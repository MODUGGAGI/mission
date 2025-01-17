package project.toy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Department;
import project.toy.domain.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByDepartment(Department department, PageRequest pageRequest);

    Page<Doctor> findAllByNameContaining(String name, Pageable pageable);
}
