package project.toy.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
