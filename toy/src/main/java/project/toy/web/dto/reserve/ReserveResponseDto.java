package project.toy.web.dto.reserve;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.toy.domain.enums.ReserveStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ReserveResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReserveJoinResultDTO {
        Long id;
        String hospitalName;
        String departmentName;
        String doctorName;
        String patientName;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime treatmentTime;
        ReserveStatus status;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TreatmentResultDTO {
        private Long reserveId;
        private String patientName;
        private String doctorName;
        private ReserveStatus status;
        private String price;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReserveListDTO {
        List<ReserveDTO> reserveList;
        Integer listSize;
        boolean isFirst;
        boolean isLast;
        Integer totalPage;
        Long totalElements;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReserveDTO {
        String patientName;
        String doctorName;
        String departmentName;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime treatmentTime;
        ReserveStatus status;
        String price;
    }
}
