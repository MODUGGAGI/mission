package project.toy.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    // 멤버 관려 에러
    PATIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "PATIENT4001", "환자가 없습니다."),
    // 의사 관련 에러
    DOCTOR_NOT_FOUND(HttpStatus.NOT_FOUND, "DOCTOR4001", "의사가 존재하지 않습니다."),
    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    //Page의 범위가 맞지 않음
    PAGE_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, "PAGE4001","페이지 범위에 맞지 않는 페이지 값이 들어왔습니다."),
    //전화번호 형식 오류
    NUMBER_INAPPROPRIATE(HttpStatus.BAD_REQUEST,"NUMBER4001","전화번호 형식에 맞지 않는 값입니다."),
    //gender 관련 오류
    GENDER_INAPPROPRIATE(HttpStatus.BAD_REQUEST,"GENDER4001","gender값은 1과 2 사이여야 합니다."),
    //age 관련 오류
    AGE_INAPPROPRIATE(HttpStatus.BAD_REQUEST,"AGE4001","Age값은 음수 일 수 없습니다."),
    //hospital 관련오류
    HOSPITAL_NOT_FOUND(HttpStatus.NOT_FOUND, "HOSPITAL4001", "병원을 찾을 수 없습니다."),
    //department 관련오류
    DEPARTMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "DEPARTMENT4001", "진료과를 찾을 수 없습니다."),
    DEPARTMENT_NOT_EXISTS(HttpStatus.NOT_FOUND, "DEPARTMENT4002","해당 병원에는 해당 진료과가 존재하지 않습니다."),
    //reserve 관련오류
    RESERVE_STATUS_NOTFOUND(HttpStatus.NOT_FOUND,"RESERVE4001","예약 상태는 1(WALK_IN),2(RESERVE) 중 선택해야 합니다."),
    RESERVE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"RESERVE4002","해당 시간에 이미 예약이 존재합니다."),
    RESERVE_STATUS_CHANGE_DENIED(HttpStatus.BAD_REQUEST,"RESERVE4003","예약 상태에서만 의사 변경이 가능합니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
