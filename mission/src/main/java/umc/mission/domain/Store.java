package umc.mission.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.mission.domain.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(columnDefinition = "float DEFAULT 0")
    private Float score;

    @Column(columnDefinition = "int DEFAULT 0")
    private Integer reviewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    public void setRegion(Region region) {
        if (this.region != null) {
            this.region.getStoreList().remove(this);
        }
        this.region = region;
        region.getStoreList().add(this);
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    public void addReviewScore(Float newReviewScore) {
        if (newReviewScore == null) {
            newReviewScore = 0F;
        }

        // 새로운 리뷰 평균 계산
        float oldTotal = (this.score == null ? 0F : this.score) * this.reviewCount;
        float newTotal = oldTotal + newReviewScore;

        this.reviewCount += 1;
        float newAvg = newTotal / this.reviewCount;

        // 소수 첫째 자리 반올림
        this.score = (float)Math.round(newAvg * 10) / 10.0f;
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();
    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", region=" + (region != null ? region.getName() : "N/A") + // region의 이름 출력
                '}';
    }
}
