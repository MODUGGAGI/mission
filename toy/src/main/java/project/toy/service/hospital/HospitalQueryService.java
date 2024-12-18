package project.toy.service.hospital;

import project.toy.web.dto.hospital.HospitalResponseDto;

public interface HospitalQueryService {
    HospitalResponseDto.HospitalDTO getHospital(Long id);
    HospitalResponseDto.HospitalListDTO getHospitals(Integer page);
}
