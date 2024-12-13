package project.toy.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.ReserveHandler;
import project.toy.domain.Doctor;
import project.toy.domain.Patient;
import project.toy.domain.Reserve;
import project.toy.domain.enums.ReserveStatus;
import project.toy.web.dto.reserve.ReserveRequestDto;
import project.toy.web.dto.reserve.ReserveResponseDto;

import java.util.List;
import java.util.Locale;

public class ReserveConverter {

    static PriceFormatter formatter = new PriceFormatter();

    public static Reserve toReserve(ReserveRequestDto.ReserveJoinDTO request, Patient patient, Doctor doctor) {

        ReserveStatus status = null;
        switch (request.getStatus()) {
            case 1 -> status = ReserveStatus.WALK_IN;
            case 2 -> status = ReserveStatus.RESERVE;
            default -> throw new ReserveHandler(ErrorStatus.RESERVE_STATUS_NOTFOUND);
        }

        return Reserve.builder()
                .patient(patient)
                .doctor(doctor)
                .treatmentTime(request.getTreatmentTime())
                .status(status)
                .build();
    }

    public static ReserveResponseDto.ReserveJoinResultDTO toReserveJoinResultDTO(Reserve reserve) {
        return ReserveResponseDto.ReserveJoinResultDTO.builder()
                .id(reserve.getId())
                .hospitalName(reserve.getDoctor().getDepartment().getHospital().getName())
                .departmentName(reserve.getDoctor().getDepartment().getName())
                .doctorName(reserve.getDoctor().getName())
                .patientName(reserve.getPatient().getName())
                .treatmentTime(reserve.getTreatmentTime())
                .status(reserve.getStatus())
                .build();
    }

    public static ReserveResponseDto.TreatmentResultDTO toTreatmentResultDTO(Reserve reserve) {

        String price = reserve.getPrice() == null ? "아직 진료 전, 진료비 X" : formatter.print(reserve.getPrice(), Locale.KOREA) + "원" ;

        return ReserveResponseDto.TreatmentResultDTO.builder()
                .reserveId(reserve.getId())
                .patientName(reserve.getPatient().getName())
                .doctorName(reserve.getDoctor().getName())
                .status(reserve.getStatus())
                .price(price)
                .build();
    }

    public static ReserveResponseDto.ReserveListDTO toReserveListDTO(Page<Reserve> reservePage) {
        List<ReserveResponseDto.ReserveDTO> reserveDTOList
                = reservePage.getContent().stream().map(ReserveConverter::toReserveDTO).toList();

        return ReserveResponseDto.ReserveListDTO.builder()
                .reserveList(reserveDTOList)
                .listSize(reserveDTOList.size())
                .isFirst(reservePage.isFirst())
                .isLast(reservePage.isLast())
                .totalPage(reservePage.getTotalPages())
                .totalElements(reservePage.getTotalElements())
                .build();
    }

    private static ReserveResponseDto.ReserveDTO toReserveDTO(Reserve reserve) {

        String price = reserve.getPrice() == null ? "아직 진료 전, 진료비 X" : formatter.print(reserve.getPrice(), Locale.KOREA) + "원" ;

        return ReserveResponseDto.ReserveDTO.builder()
                .patientName(reserve.getPatient().getName())
                .doctorName(reserve.getDoctor().getName())
                .departmentName(reserve.getDoctor().getDepartment().getName())
                .treatmentTime(reserve.getTreatmentTime())
                .status(reserve.getStatus())
                .price(price)
                .build();
    }
}
