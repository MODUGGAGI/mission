package umc.mission.service.storeservice;

import org.springframework.data.domain.Page;
import umc.mission.domain.Mission;
import umc.mission.domain.Review;
import umc.mission.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    Page<Review> getReviewList(Long storeId, Integer page);
    Page<Mission> getMissionList(Long storeId, Integer page);
}
