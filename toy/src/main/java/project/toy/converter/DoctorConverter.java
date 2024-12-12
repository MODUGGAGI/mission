package project.toy.converter;

import project.toy.domain.Doctor;
import project.toy.domain.embeddable.PhoneNum;
import project.toy.web.dto.doctor.DoctorRequestDto;
import project.toy.web.dto.doctor.DoctorResponseDto;

import java.time.LocalDate;

public class DoctorConverter {

    public static Doctor toDoctor(DoctorRequestDto.DoctorJoinDTO request) {
        return Doctor.builder()
                .name(request.getName())
                .phoneNum(PhoneNum.builder().phoneNum(request.getPhoneNum()).build())
                .career(request.getCareer())
                .startDate(LocalDate.now())
                .build();
    }

    public static DoctorResponseDto.DoctorJoinResultDTO toDoctorJoinResultDTO(Doctor doctor) {
        return DoctorResponseDto.DoctorJoinResultDTO.builder()
                .id(doctor.getId())
                .doctorName(doctor.getName())
                .departmentName(doctor.getDepartment().getName())
                .hospitalName(doctor.getDepartment().getHospital().getName())
                .startDate(doctor.getStartDate())
                .build();
    }
}
