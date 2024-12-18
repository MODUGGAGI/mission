package project.toy.service.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.HospitalHandler;
import project.toy.converter.HospitalConverter;
import project.toy.domain.Hospital;
import project.toy.repository.HospitalRepository;
import project.toy.web.dto.hospital.HospitalResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalQueryServiceImpl implements HospitalQueryService{

    private final HospitalRepository hospitalRepository;

    @Override
    public HospitalResponseDto.HospitalDTO getHospital(Long id) {
        return HospitalConverter.toHospitalDTO(hospitalRepository.findById(id).orElseThrow(() -> new HospitalHandler(ErrorStatus.HOSPITAL_NOT_FOUND)));
    }

    @Override
    public HospitalResponseDto.HospitalListDTO getHospitals(Integer page) {
        return HospitalConverter.toHospitalListDTO(hospitalRepository.findAll(PageRequest.of(page, 10)));
    }
}
