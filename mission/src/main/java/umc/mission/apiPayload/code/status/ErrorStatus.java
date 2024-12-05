package umc.mission.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.mission.apiPayload.code.BaseErrorCode;
import umc.mission.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    //Food Category 오류
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY4001", "음식 카테고리가 없습니다."),

    //Region 오류
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION4001", "지역이 없습니다."),

    //Store 오류
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "가게가 없습니다."),

    //Mission 오류
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "미션 없습니다."),

    //이미 도전중인 미션 존재
    MISSION_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBERMISSION4001", "이미 도전중인 미션이 있습니다."),
    MISSION_NOT_CHALLENGING(HttpStatus.NOT_FOUND, "MEMBERMISSION4002", "도전 중인 미션이 없습니다."),
    MISSION_ALREADY_COMPLETE(HttpStatus.BAD_REQUEST, "MEMBERMISSION4003", "이미 완료한 미션입니다."),

    //Page의 범위가 맞지 않음
    PAGE_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, "PAGE4001","페이지 범위에 맞지 않는 페이지 값이 들어왔습니다.");


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
