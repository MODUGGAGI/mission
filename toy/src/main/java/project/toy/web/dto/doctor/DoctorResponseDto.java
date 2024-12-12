package project.toy.web.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class DoctorResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DoctorJoinResultDTO {
        Long id;
        String doctorName;
        String departmentName;
        String hospitalName;
        LocalDate startDate;
    }
}
