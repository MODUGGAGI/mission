package umc.mission.repository.storerepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mission.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
