package umc.mission.converter;

import org.springframework.data.domain.Page;
import umc.mission.domain.Member;
import umc.mission.domain.Review;
import umc.mission.domain.enums.Gender;
import umc.mission.domain.mapping.MemberMission;
import umc.mission.web.dto.member.MemberRequestDto;
import umc.mission.web.dto.member.MemberResponseDto;
import umc.mission.web.dto.membermission.MemberMissionResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberConverter { //객체를 DTO로 바꾸는 역할

    public static MemberResponseDto.MemberJoinResultDto toJoinResultDto(Member member) {
        return MemberResponseDto.MemberJoinResultDto.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDto.MemberJoinDto request) {

        Gender gender = null;
        switch (request.getGender()) {
            case 1-> gender=Gender.MALE;
            case 2-> gender = Gender.FEMALE;
            case 3 -> gender = Gender.NONE;
        }

        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .gender(gender)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .role(request.getRole())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDto.MyReviewDTO toMyReviewDTO(Review review) {
        return MemberResponseDto.MyReviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static MemberResponseDto.MyReviewListDTO toMyReviewListDTO(Page<Review> reviewList) {

        List<MemberResponseDto.MyReviewDTO> ReviewDTOList
                = reviewList.stream().map(MemberConverter::toMyReviewDTO).toList();

        return MemberResponseDto.MyReviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(ReviewDTOList.size())
                .reviewList(ReviewDTOList)
                .build();
    }
}
