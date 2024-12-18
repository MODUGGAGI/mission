package project.toy.service.patient;

import project.toy.web.dto.patient.PatientResponseDto;

public interface PatientQueryService {
    PatientResponseDto.PatientDTO getPatient(Long patientId);
    PatientResponseDto.PatientListDTO getPatients(Integer page);
    PatientResponseDto.PatientListDTO getPatientByName(String name, Integer page);
}
