package project.toy.converter;

import org.springframework.http.HttpStatus;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.AgeHandler;
import project.toy.apiPayload.exception.handler.GenderHandler;
import project.toy.domain.Patient;
import project.toy.domain.embeddable.PhoneNum;
import project.toy.domain.enums.Gender;
import project.toy.web.dto.patient.PatientRequestDto;
import project.toy.web.dto.patient.PatientResponseDto;

public class PatientConverter {

    public static Patient toPatient(PatientRequestDto.PatientJoinDTO request) {

        Gender gender = null;
        switch (request.getGender()) {
            case 1 -> gender = Gender.male;
            case 2 -> gender = Gender.female;
            default -> throw new GenderHandler(ErrorStatus.GENDER_INAPPROPRIATE);
        }

        if (request.getAge() < 0) {
            throw new AgeHandler(ErrorStatus.AGE_INAPPROPRIATE);
        }

        return Patient.builder()
                .name(request.getName())
                .age(request.getAge())
                .phoneNum(PhoneNum.builder()
                        .phoneNum(request.getNumber())
                        .build())
                .gender(gender)
                .build();
    }

    public static PatientResponseDto.PatientJoinResultDTO toPatientJoinResultDto(Patient patient) {
        return PatientResponseDto.PatientJoinResultDTO.builder()
                .patientId(patient.getId())
                .name(patient.getName())
                .build();
    }
}
