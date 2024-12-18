package project.toy.service.hospital;

import project.toy.domain.Hospital;
import project.toy.domain.Patient;
import project.toy.web.dto.hospital.HospitalRequestDto;
import project.toy.web.dto.hospital.HospitalResponseDto;

public interface HospitalCommandService {

    HospitalResponseDto.HospitalJoinResultDTO joinHospital(HospitalRequestDto.HospitalJoinDTO request);
}
