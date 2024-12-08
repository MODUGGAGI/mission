package project.toy.web.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PatientResponseDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class PatientJoinResultDTO {
        Long patientId;
        String name;
    }
}
