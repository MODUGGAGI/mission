package project.toy.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.toy.domain.enums.ReserveStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public void setPatient(Patient patient) {
        if (this.patient != null) {
            this.patient.getReserveList().remove(this);
        }
        this.patient = patient;
        this.patient.getReserveList().add(this);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public void setDoctor(Doctor doctor) {
        if (this.doctor != null) {
            this.doctor.getReserveList().remove(this);
        }
        this.doctor = doctor;
        this.doctor.getReserveList().add(this);
    }

    @CreatedDate
    @Column(columnDefinition = "datetime(6)")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private ReserveStatus status;

    private int price;
}
