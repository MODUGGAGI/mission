package umc.mission.apiPayload.exception.handler;

import umc.mission.apiPayload.code.BaseErrorCode;
import umc.mission.apiPayload.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode code) {
        super(code);
    }
}
