package project.toy.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.ReserveHandler;
import project.toy.domain.enums.ReserveStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reserve {

    @Builder
    private Reserve(Patient patient, Doctor doctor, LocalDateTime treatmentTime, ReserveStatus status) {
        setPatient(patient);
        setDoctor(doctor);
        this.treatmentTime = treatmentTime;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


    @Column(columnDefinition = "datetime")
    private LocalDateTime treatmentTime;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private ReserveStatus status;

    private Integer price;

    private void setPatient(Patient patient) {
        if (patient != null) {
            this.patient = patient;
            this.patient.getReserveList().add(this);
        }
    }

    private void setDoctor(Doctor doctor) {
        if (doctor != null) {
            this.doctor = doctor;
            this.doctor.getReserveList().add(this);
        }
    }

    public void chargePrice(Integer price) {
        this.price = price;
    }

    public void changeDoctor(Doctor doctor) {
        if (this.status != ReserveStatus.RESERVE) {
            throw new ReserveHandler(ErrorStatus.RESERVE_STATUS_CHANGE_DENIED);
        }
        if (this.doctor != null) {
            this.doctor.getReserveList().remove(this);
        }
        this.doctor = doctor;
        this.doctor.getReserveList().add(this);
    }
}
