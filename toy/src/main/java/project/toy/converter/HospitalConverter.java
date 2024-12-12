package project.toy.converter;

import org.springframework.data.domain.Page;
import project.toy.domain.Hospital;
import project.toy.domain.embeddable.PhoneNum;
import project.toy.web.dto.hospital.HospitalRequestDto;
import project.toy.web.dto.hospital.HospitalResponseDto;

import java.util.List;

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

    public static HospitalResponseDto.HospitalListDTO toHospitalListDTO(Page<Hospital> hospitalList) {
        List<HospitalResponseDto.HospitalDTO> list
                = hospitalList.stream().map(HospitalConverter::toHospitalDTO).toList();

        return HospitalResponseDto.HospitalListDTO.builder()
                .hospitalDTOList(list)
                .listSize(list.size())
                .isFirst(hospitalList.isFirst())
                .isLast(hospitalList.isLast())
                .totalPage(hospitalList.getTotalPages())
                .totalElements(hospitalList.getTotalElements())
                .build();
    }

    public static HospitalResponseDto.HospitalDTO toHospitalDTO(Hospital hospital) {
        return HospitalResponseDto.HospitalDTO.builder()
                .name(hospital.getName())
                .address(hospital.getAddress())
                .phoneNum(hospital.getPhoneNum().getPhoneNum())
                .build();
    }
}
