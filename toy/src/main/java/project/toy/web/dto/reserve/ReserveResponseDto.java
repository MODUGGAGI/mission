package project.toy.web.dto.reserve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.toy.domain.enums.ReserveStatus;

import java.time.LocalDateTime;

public class ReserveResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReserveJoinResultDTO {
        Long id;
        String hospitalName;
        String departmentName;       // 진료과 이름
        String doctorName;           // 담당 의사 이름
        String patientName;          // 환자 이름
        LocalDateTime treatmentTime; // 진료 예정 시간
        ReserveStatus status;
    }
}
