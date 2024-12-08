package project.toy.apiPayload.exception.handler;

import project.toy.apiPayload.exception.GeneralException;
import project.toy.apiPayload.code.BaseErrorCode;

public class PageHandler extends GeneralException {
    public PageHandler(BaseErrorCode code) {
        super(code);
    }
}
