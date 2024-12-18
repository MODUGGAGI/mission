package project.toy.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import project.toy.apiPayload.code.status.ErrorStatus;
import project.toy.validation.annotation.AppropriateNumber;

public class AppropriateNumberValidator implements ConstraintValidator<AppropriateNumber, String> {

    @Override
    public void initialize(AppropriateNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = (value != null && value.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$"));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.NUMBER_INAPPROPRIATE.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
