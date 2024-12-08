package project.toy.web.dto.patient;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import project.toy.validation.annotation.AppropriateNumber;

public class PatientRequestDto {
    @Getter
    public static class PatientJoinDTO {

        @NotNull
        String name;

        @NotNull
        Integer age;

        @AppropriateNumber
        String number;

        @NotNull
        Integer gender;
    }
}
