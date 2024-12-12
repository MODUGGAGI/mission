package project.toy.apiPayload.exception.handler;

import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.exception.GeneralException;

public class GenderHandler extends GeneralException {
    public GenderHandler(BaseErrorCode code) {
        super(code);
    }
}
