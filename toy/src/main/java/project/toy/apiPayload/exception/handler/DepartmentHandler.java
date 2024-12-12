package project.toy.apiPayload.exception.handler;

import project.toy.apiPayload.code.BaseErrorCode;
import project.toy.apiPayload.exception.GeneralException;

public class DepartmentHandler extends GeneralException {
    public DepartmentHandler(BaseErrorCode code) {
        super(code);
    }
}
