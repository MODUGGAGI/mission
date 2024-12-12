package project.toy.web.dto.hospital;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HospitalResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HospitalJoinResultDTO {
        Long hospitalId;
        String name;
    }
}
