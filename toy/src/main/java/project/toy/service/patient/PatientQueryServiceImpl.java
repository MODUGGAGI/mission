package project.toy.service.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.PatientHandler;
import project.toy.converter.PatientConverter;
import project.toy.domain.Patient;
import project.toy.repository.PatientRepository;
import project.toy.web.dto.patient.PatientResponseDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientQueryServiceImpl implements PatientQueryService{

    private final PatientRepository patientRepository;

    @Override
    public PatientResponseDto.PatientDTO getPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientHandler(ErrorStatus.PATIENT_NOT_FOUND));
        return PatientConverter.toPatientDTO(patient);
    }

    @Override
    public PatientResponseDto.PatientListDTO getPatients(Integer page) {
        Page<Patient> patientPage = patientRepository.findAll(PageRequest.of(page, 10));
        return PatientConverter.toPatientListDTO(patientPage);
    }

    @Override
    public PatientResponseDto.PatientListDTO getPatientByName(String name, Integer page) {
        Page<Patient> patients = patientRepository.findAllByNameContaining(name, PageRequest.of(page, 10));
        return PatientConverter.toPatientListDTO(patients);
    }
}
