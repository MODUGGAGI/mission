package umc.mission.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreRequestDto {

    @Getter
    public static class JoinDto{
        String name;
        String address;

        Long regionId;
    }
}
