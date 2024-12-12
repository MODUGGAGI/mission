package project.toy.service.reserve;

import project.toy.domain.Reserve;
import project.toy.web.dto.reserve.ReserveRequestDto;

public interface ReserveCommandService {
    public Reserve joinReserve(ReserveRequestDto.ReserveJoinDTO request);
}
