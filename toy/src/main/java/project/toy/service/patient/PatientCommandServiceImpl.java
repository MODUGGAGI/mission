package project.toy.service.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.converter.PatientConverter;
import project.toy.domain.Patient;
import project.toy.repository.PatientRepository;
import project.toy.web.dto.patient.PatientRequestDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientCommandServiceImpl implements PatientCommandService{

    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public Patient joinPatient(PatientRequestDto.PatientJoinDTO request) {
        Patient patient = PatientConverter.toPatient(request);

        return patientRepository.save(patient);
    }
}
