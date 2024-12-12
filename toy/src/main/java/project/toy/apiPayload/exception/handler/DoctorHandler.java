package project.toy.apiPayload.exception.handler;

import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.exception.GeneralException;

public class DoctorHandler extends GeneralException {
    public DoctorHandler(BaseErrorCode code) {
        super(code);
    }
}
