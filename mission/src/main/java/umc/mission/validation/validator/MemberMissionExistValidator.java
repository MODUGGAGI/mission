package umc.mission.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.mission.apiPayload.code.status.ErrorStatus;
import umc.mission.apiPayload.exception.handler.MemberHandler;
import umc.mission.apiPayload.exception.handler.MissionHandler;
import umc.mission.domain.Member;
import umc.mission.domain.Mission;
import umc.mission.repository.MemberRepository;
import umc.mission.repository.MissionRepository;
import umc.mission.service.membermissionservice.MemberMissionService;
import umc.mission.validation.annotation.ExistMemberMission;
import umc.mission.validation.annotation.ExistStore;
import umc.mission.web.dto.membermission.MemberMissionRequestDto;

@Component
@RequiredArgsConstructor
public class MemberMissionExistValidator implements ConstraintValidator<ExistMemberMission, MemberMissionRequestDto.MemberMissionJoinDto> {

    private final MemberMissionService memberMissionService;

    @Override
    public void initialize(ExistMemberMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionRequestDto.MemberMissionJoinDto value, ConstraintValidatorContext context) {
        boolean isValid = memberMissionService.alreadyChallenging(value.getMemberId(), value.getMissionId());

        if (isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_EXISTS.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}