package project.toy.service.search;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import project.toy.converter.SearchConverter;
import project.toy.domain.Department;
import project.toy.domain.Doctor;
import project.toy.domain.Hospital;
import project.toy.repository.DepartmentRepository;
import project.toy.repository.DoctorRepository;
import project.toy.repository.HospitalRepository;
import project.toy.web.dto.search.SearchResponseDto;

@Service
@RequiredArgsConstructor
public class SearchQueryServiceImpl implements SearchQueryService {

    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public SearchResponseDto.HospitalSearchResultDTO getSearchResult(String name, Integer page) {
        Page<Hospital> hospitals = hospitalRepository.findAllByNameContaining(name, PageRequest.of(page, 10));
        Page<Department> departments = departmentRepository.findAllByNameContaining(name, PageRequest.of(page, 10));
        Page<Doctor> doctors = doctorRepository.findAllByNameContaining(name, PageRequest.of(page, 10));

        return SearchConverter.toSearchResult(hospitals, departments, doctors);
    }
}
