package project.toy.converter;

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

    public static PatientResponseDto.PatientJoinResultDTO toJoinResultDto(Patient patient) {
        return PatientResponseDto.PatientJoinResultDTO.builder()
                .patientId(patient.getId())
                .name(patient.getName())
                .build();
    }
}
