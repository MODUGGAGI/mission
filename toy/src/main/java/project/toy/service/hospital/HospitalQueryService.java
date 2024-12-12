package project.toy.service.hospital;

import org.springframework.data.domain.Page;
import project.toy.domain.Hospital;

public interface HospitalQueryService {
    Page<Hospital> getHospital(Integer page);
}
