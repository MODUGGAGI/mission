package project.toy.apiPayload.exception.handler;

import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.exception.GeneralException;

public class AgeHandler extends GeneralException {
    public AgeHandler(BaseErrorCode code) {
        super(code);
    }
}
