package project.toy.web.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import project.toy.validation.annotation.AppropriateNumber;

import java.time.LocalDate;

public class DoctorRequestDto {

    @Getter
    public static class DoctorJoinDTO {
        @NotBlank
        String name;

        @NotBlank
        @AppropriateNumber
        String phoneNum;

        @NotNull
        int career;
    }
}
