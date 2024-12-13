package project.toy.domain;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.*;
import project.toy.domain.embeddable.PhoneNum;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Builder
    private Department(String name, Hospital hospital, PhoneNum phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
        setHospital(hospital);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    private void setHospital(Hospital hospital) {
        if (this.hospital != null) {
            this.hospital = hospital;
            this.hospital.getDepartments().add(this);
        }
    }

    private PhoneNum phoneNum;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors = new ArrayList<>();
}
