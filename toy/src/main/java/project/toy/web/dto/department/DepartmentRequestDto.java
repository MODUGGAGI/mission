package project.toy.web.dto.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import project.toy.validation.annotation.AppropriateNumber;

public class DepartmentRequestDto {

    @Getter
    public static class DepartmentJoinDTO {

        @NotBlank
        String name;

        @AppropriateNumber
        @NotBlank
        String phoneNum;

    }
}
