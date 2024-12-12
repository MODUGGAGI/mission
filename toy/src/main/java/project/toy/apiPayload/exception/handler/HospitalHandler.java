package project.toy.apiPayload.exception.handler;

import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.exception.GeneralException;

public class HospitalHandler extends GeneralException {
    public HospitalHandler(BaseErrorCode code) {
        super(code);
    }
}
