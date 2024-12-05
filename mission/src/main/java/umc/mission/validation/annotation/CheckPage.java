package umc.mission.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.mission.validation.validator.StoreExistValidator;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "페이지 범위가 너무 작습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
