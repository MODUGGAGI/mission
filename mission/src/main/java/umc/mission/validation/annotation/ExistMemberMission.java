package umc.mission.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.mission.validation.validator.MemberMissionExistValidator;
import umc.mission.validation.validator.StoreExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberMissionExistValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMemberMission {

    String message() default "이미 해당 미션을 도전중입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}