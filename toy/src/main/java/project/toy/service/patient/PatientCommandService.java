package project.toy.service.patient;

import project.toy.domain.Patient;
import project.toy.web.dto.patient.PatientRequestDto;
import project.toy.web.dto.patient.PatientResponseDto;

public interface PatientCommandService {
    PatientResponseDto.PatientJoinResultDTO joinPatient(PatientRequestDto.PatientJoinDTO request);
}
