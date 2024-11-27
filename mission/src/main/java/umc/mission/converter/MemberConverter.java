package umc.mission.converter;

import umc.mission.domain.Member;
import umc.mission.domain.enums.Gender;
import umc.mission.web.dto.MemberRequestDto;
import umc.mission.web.dto.MemberResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter { //객체를 DTO로 바꾸는 역할

    public static MemberResponseDto.JoinResultDto toJoinResultDto(Member member) {
        return MemberResponseDto.JoinResultDto.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDto.JoinDto request) {

        Gender gender = null;
        switch (request.getGender()) {
            case 1-> gender=Gender.MALE;
            case 2-> gender = Gender.FEMALE;
            case 3 -> gender = Gender.NONE;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
