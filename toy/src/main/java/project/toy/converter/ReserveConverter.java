package project.toy.converter;

import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.apiPayload.exception.handler.ReserveHandler;
import project.toy.domain.Doctor;
import project.toy.domain.Patient;
import project.toy.domain.Reserve;
import project.toy.domain.enums.ReserveStatus;
import project.toy.web.dto.reserve.ReserveRequestDto;
import project.toy.web.dto.reserve.ReserveResponseDto;

public class ReserveConverter {

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
}
