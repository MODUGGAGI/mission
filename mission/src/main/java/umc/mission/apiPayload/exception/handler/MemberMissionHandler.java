package umc.mission.apiPayload.exception.handler;

import umc.mission.apiPayload.code.BaseErrorCode;
import umc.mission.apiPayload.exception.GeneralException;

public class MemberMissionHandler extends GeneralException {
    public MemberMissionHandler(BaseErrorCode code) {
        super(code);
    }
}
