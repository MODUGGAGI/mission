package project.toy.apiPayload.exception.handler;

import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.exception.GeneralException;

public class ReserveHandler extends GeneralException {
    public ReserveHandler(BaseErrorCode code) {
        super(code);
    }
}
