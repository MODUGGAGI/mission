package umc.mission.apiPayload.exception.handler;

import umc.mission.apiPayload.code.BaseErrorCode;
import umc.mission.apiPayload.exception.GeneralException;

public class PageHandler extends GeneralException {
    public PageHandler(BaseErrorCode code) {
        super(code);
    }
}
