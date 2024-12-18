package project.toy.web.dto.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentListDTO {
        List<DepartmentDTO> departmentDTOList;
        Integer listSize;
        boolean isFirst;
        boolean isLast;
        Integer totalPage;
        Long totalElement;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentDTO {
        String departmentName;
        String phoneNum;
    }
}
