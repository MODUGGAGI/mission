package project.toy.converter;

import org.springframework.data.domain.Page;
import project.toy.domain.Department;
import project.toy.domain.Doctor;
import project.toy.domain.embeddable.PhoneNum;
import project.toy.web.dto.doctor.DoctorRequestDto;
import project.toy.web.dto.doctor.DoctorResponseDto;

import java.time.LocalDate;
import java.util.List;

public class DoctorConverter {

    public static Doctor toDoctor(DoctorRequestDto.DoctorJoinDTO request, Department department) {
        return Doctor.builder()
                .department(department)
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

    public static DoctorResponseDto.DoctorListDTO toDoctorListDTO(Page<Doctor> doctorList) {
        List<DoctorResponseDto.DoctorDTO> list =
                doctorList.stream()
                        .map(DoctorConverter::toDoctorDTO)
                        .toList();

        return DoctorResponseDto.DoctorListDTO.builder()
                .doctorDTOList(list)
                .listSize(list.size())
                .isFirst(doctorList.isFirst())
                .isLast(doctorList.isLast())
                .totalPage(doctorList.getTotalPages())
                .totalElements(doctorList.getTotalElements())
                .build();
    }

    public static DoctorResponseDto.DoctorDTO toDoctorDTO(Doctor doctor) {
        return DoctorResponseDto.DoctorDTO.builder()
                .doctorName(doctor.getName())
                .departmentName(doctor.getDepartment().getName())  // 진료과 이름 추가
                .phoneNum(doctor.getPhoneNum().getPhoneNum())
                .career(doctor.getCareer())
                .build();
    }
}
