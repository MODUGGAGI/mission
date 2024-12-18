package project.toy.web.dto.patient;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import project.toy.validation.annotation.AppropriateNumber;

public class PatientRequestDto {
    @Getter
    public static class PatientJoinDTO {

        @NotBlank
        String name;

        @NotNull
        Integer age;

        @AppropriateNumber
        @NotBlank
        String number;

        @NotNull
        Integer gender;
    }
}
