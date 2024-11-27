package project.toy.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Reserve> reserveList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public void setDepartment(Department department) {
        if (this.department != null) {
            this.department.getDoctors().remove(this);
        }
        this.department = department;
        this.department.getDoctors().add(this);
    }

    private int career;
}
