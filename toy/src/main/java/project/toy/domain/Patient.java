package project.toy.domain;

import jakarta.persistence.*;
import lombok.*;
import project.toy.domain.embeddable.PhoneNum;
import project.toy.domain.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;
    private int age;
    private PhoneNum phoneNum;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Reserve> reserveList = new ArrayList<>();
}
