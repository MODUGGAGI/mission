package project.toy.web.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DoctorListDTO {
        List<DoctorDTO> doctorDTOList;
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
    public static class DoctorDTO {
        String doctorName;
        String departmentName;
        String phoneNum;
        int career;
    }
}
