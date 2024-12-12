package project.toy.service.reserve;

import org.springframework.data.domain.Page;
import project.toy.domain.Reserve;
import project.toy.domain.enums.ReserveStatus;
import project.toy.web.dto.reserve.ReserveResponseDto;

import java.time.LocalDate;

public interface ReserveQueryService {
    ReserveResponseDto.ReserveListDTO getPatientReserves(Long patientId, Integer statusFilter, Integer page);
    ReserveResponseDto.ReserveListDTO getDoctorReserves(Long hospitalId, Long departmentId, Long doctorId,
                                                        Integer statusFilter, Integer page);
}
