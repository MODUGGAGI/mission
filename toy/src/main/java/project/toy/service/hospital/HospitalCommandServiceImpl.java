package project.toy.service.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.converter.HospitalConverter;
import project.toy.domain.Hospital;
import project.toy.repository.HospitalRepository;
import project.toy.web.dto.hospital.HospitalRequestDto;
import project.toy.web.dto.hospital.HospitalResponseDto;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalCommandServiceImpl implements HospitalCommandService {

    private final HospitalRepository hospitalRepository;

    @Override
    @Transactional
    public HospitalResponseDto.HospitalJoinResultDTO joinHospital(HospitalRequestDto.HospitalJoinDTO request) {
        Hospital hospital = HospitalConverter.toHospital(request);

        return HospitalConverter.toHospitalJoinResultDto(hospitalRepository.save(hospital));
    }
}
