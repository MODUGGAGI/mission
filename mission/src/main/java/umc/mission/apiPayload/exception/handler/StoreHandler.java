package umc.mission.apiPayload.exception.handler;

import umc.mission.apiPayload.code.BaseErrorCode;
import umc.mission.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode code) {
        super(code);
    }
}
