package project.toy.converter;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.AgeHandler;
import project.toy.apiPayload.exception.handler.GenderHandler;
import project.toy.domain.Patient;
import project.toy.domain.embeddable.PhoneNum;
import project.toy.domain.enums.Gender;
import project.toy.web.dto.patient.PatientRequestDto;
import project.toy.web.dto.patient.PatientResponseDto;

import java.util.List;

public class PatientConverter {

    public static Patient toPatient(PatientRequestDto.PatientJoinDTO request) {

        final int MALE = 1;
        final int FEMALE = 2;

        Gender gender = null;
        switch (request.getGender()) {
            case MALE -> gender = Gender.male;
            case FEMALE -> gender = Gender.female;
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

    public static PatientResponseDto.PatientDTO toPatientDTO(Patient patient) {
        return PatientResponseDto.PatientDTO.builder()
                .name(patient.getName())
                .age(patient.getAge())
                .gender(patient.getGender())
                .phoneNum(patient.getPhoneNum().getPhoneNum())
                .build();
    }

    public static PatientResponseDto.PatientListDTO toPatientListDTO(Page<Patient> patientPage) {

        List<PatientResponseDto.PatientDTO> list
                = patientPage.stream().map(PatientConverter::toPatientDTO).toList();

        return PatientResponseDto.PatientListDTO.builder()
                .patientList(list)
                .isFirst(patientPage.isFirst())
                .isLast(patientPage.isLast())
                .totalElements(patientPage.getTotalElements())
                .totalPage(patientPage.getTotalPages())
                .listSize(list.size())
                .build();
    }
}
