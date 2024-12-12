package project.toy.service.hospital;

import project.toy.domain.Hospital;
import project.toy.domain.Patient;
import project.toy.web.dto.hospital.HospitalRequestDto;

public interface HospitalCommandService {

    public Hospital joinHospital(HospitalRequestDto.HospitalJoinDTO request);
}
