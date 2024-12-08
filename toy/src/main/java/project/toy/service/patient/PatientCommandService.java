package project.toy.service.patient;

import project.toy.domain.Patient;
import project.toy.web.dto.patient.PatientRequestDto;

public interface PatientCommandService {
    Patient joinPatient(PatientRequestDto.PatientJoinDTO request);
}
