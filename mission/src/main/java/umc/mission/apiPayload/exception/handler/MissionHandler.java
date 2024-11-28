package umc.mission.apiPayload.exception.handler;

import umc.mission.apiPayload.code.BaseErrorCode;
import umc.mission.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode code) {
        super(code);
    }
}
