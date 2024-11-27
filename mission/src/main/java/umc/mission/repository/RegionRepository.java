package umc.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mission.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
