package umc.mission.converter;

import umc.mission.web.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TestTempDTO toTempTestDTO() {
        return TempResponse.TestTempDTO.builder()
                .testString("This is Test!!")
                .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag) {
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
