package umc.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.mission.domain.Member;
import umc.mission.domain.Review;
import umc.mission.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findAllByMember(Member member, PageRequest pageRequest);
}
