package umc.mission.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.domain.base.BaseEntity;
import umc.mission.domain.enums.MissionStatus;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'CHALLENGING'")
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberMissionList().remove(this);
        }
        this.member = member;
        this.member.getMemberMissionList().add(this);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void setMission(Mission mission) {
        if (this.mission != null) {
            this.mission.getMemberMissionList().remove(this);
        }
        this.mission = mission;
        this.mission.getMemberMissionList().add(this);
    }
}
