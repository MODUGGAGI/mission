package project.toy.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
