package umc.mission.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.mission.domain.Member;
import umc.mission.domain.base.BaseEntity;
import umc.mission.domain.FoodCategory;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberPreferList().remove(this);
        }
        this.member = member;
        member.getMemberPreferList().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        if (this.foodCategory != null) {
            this.foodCategory.getMemberPreferList().remove(this);
        }
        this.foodCategory = foodCategory;
        foodCategory.getMemberPreferList().add(this);
    }
}
