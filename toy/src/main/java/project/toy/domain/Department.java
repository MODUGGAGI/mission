package project.toy.domain;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    private int phone;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors = new ArrayList<>();
}
