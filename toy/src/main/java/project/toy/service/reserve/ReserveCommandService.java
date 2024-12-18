package project.toy.service.reserve;

import project.toy.domain.Reserve;
import project.toy.web.dto.reserve.ReserveRequestDto;
import project.toy.web.dto.reserve.ReserveResponseDto;

public interface ReserveCommandService {
    ReserveResponseDto.ReserveJoinResultDTO joinReserve(ReserveRequestDto.ReserveJoinDTO request);

    ReserveResponseDto.TreatmentResultDTO completeTreatment(Long reserveId, Integer price);
}
