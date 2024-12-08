package project.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.toy.domain.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
