package project.toy.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.toy.domain.embeddable.PhoneNum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class Doctor {
    @Builder
    public Doctor(String name, Department department, PhoneNum phoneNum, LocalDate startDate, int career) {
        setDepartment(department);
        this.name = name;
        this.phoneNum = phoneNum;
        this.startDate = startDate;
        this.career = career;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Reserve> reserveList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    private PhoneNum phoneNum;

    @CreatedDate
    @Column(columnDefinition = "date")
    private LocalDate startDate;

    @Column(columnDefinition = "int default 0")
    private int career;

    private void setDepartment(Department department) {
        if (department != null) {
            this.department = department;
            this.department.getDoctors().add(this);
        }
    }

    public int updateCareerYear() {
        int calculatedCareer = LocalDate.now().getYear() - this.startDate.getYear() + this.career;

        if (LocalDate.now().isBefore(this.startDate.withYear(LocalDate.now().getYear()))) {
            //if문 조건의 withYear를 통해 년도를 now()에 해당하는 날짜로 맞춘다.
            calculatedCareer--;
        }

        if (calculatedCareer > this.career) {
            this.career = calculatedCareer;
        }
        return this.career;
    }
}
