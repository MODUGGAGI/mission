package project.toy.web.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import project.toy.domain.enums.Gender;

import java.util.List;

public class PatientResponseDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class PatientJoinResultDTO {
        Long patientId;
        String name;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class PatientDTO {
        String name;
        int age;
        Gender gender;
        String phoneNum;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PatientListDTO {
        List<PatientDTO> patientList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
