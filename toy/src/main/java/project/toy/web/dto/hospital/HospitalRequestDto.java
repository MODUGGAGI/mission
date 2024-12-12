package project.toy.web.dto.hospital;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import project.toy.validation.annotation.AppropriateNumber;

public class HospitalRequestDto {

    @Getter
    public static class HospitalJoinDTO {
        @NotBlank
        String name;

        @NotBlank
        String address;

        @AppropriateNumber
        @NotBlank
        String phoneNum;
    }
}
