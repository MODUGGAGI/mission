package project.toy.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import project.toy.validation.validator.AppropriateNumberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AppropriateNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AppropriateNumber {

    String message() default "전화번호 형식이 적절하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
