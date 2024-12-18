package project.toy.apiPayload.exception.handler;

import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.exception.GeneralException;

public class PatientHandler extends GeneralException {
    public PatientHandler(BaseErrorCode code) {
        super(code);
    }
}
