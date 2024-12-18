package project.toy.apiPayload.exception.handler;


import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
