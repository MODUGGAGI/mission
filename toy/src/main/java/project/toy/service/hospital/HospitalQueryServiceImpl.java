package project.toy.service.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import project.toy.domain.Hospital;
import project.toy.repository.HospitalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalQueryServiceImpl implements HospitalQueryService{

    private final HospitalRepository hospitalRepository;
    @Override
    public Page<Hospital> getHospital(Integer page) {
        return hospitalRepository.findAll(PageRequest.of(page, 10));
    }
}
