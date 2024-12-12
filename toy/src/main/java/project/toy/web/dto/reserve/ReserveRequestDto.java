package project.toy.web.dto.reserve;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import project.toy.domain.enums.ReserveStatus;

import java.time.LocalDateTime;

public class ReserveRequestDto {

    @Getter
    public static class ReserveJoinDTO {
        @NotNull
        Long patientId;
        @NotNull
        Long doctorId;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime treatmentTime;

        @NotNull
        Integer status;
    }
}
