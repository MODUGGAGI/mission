package umc.mission.apiPayload.exception.handler;

import umc.mission.apiPayload.code.BaseErrorCode;
import umc.mission.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
