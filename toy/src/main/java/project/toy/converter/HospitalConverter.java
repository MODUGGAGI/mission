package project.toy.converter;

import project.toy.domain.Hospital;
import project.toy.domain.Patient;
import project.toy.domain.embeddable.PhoneNum;
import project.toy.web.dto.hospital.HospitalRequestDto;
import project.toy.web.dto.hospital.HospitalResponseDto;

public class HospitalConverter {
    public static Hospital toHospital(HospitalRequestDto.HospitalJoinDTO request) {
        return Hospital.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNum(PhoneNum.builder().phoneNum(request.getPhoneNum()).build())
                .build();
    }

    public static HospitalResponseDto.HospitalJoinResultDTO toHospitalJoinResultDto(Hospital hospital) {
        return HospitalResponseDto.HospitalJoinResultDTO.builder()
                .hospitalId(hospital.getId())
                .name(hospital.getName())
                .build();
    }
}
