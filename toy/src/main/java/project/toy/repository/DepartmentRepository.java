package project.toy.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Department;
import project.toy.domain.Hospital;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Page<Department> findAllByHospital(Hospital hospital, PageRequest pageRequest);

    Page<Department> findAllByNameContaining(String name, Pageable pageable);
}
