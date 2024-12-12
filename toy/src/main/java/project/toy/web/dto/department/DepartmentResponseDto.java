package project.toy.web.dto.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DepartmentResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentJoinResultDTO {
        Long id;
        String hospitalName;
        String departmentName;
    }
}
