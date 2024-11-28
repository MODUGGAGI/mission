package umc.mission.web.dto.store;

import lombok.Getter;

public class StoreRequestDto {

    @Getter
    public static class StoreJoinDto {
        String name;
        String address;

        Long regionId;
    }
}
