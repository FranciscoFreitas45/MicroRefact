package com.project.stockexchangeappbackend.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class DateIsAfterNowValidation implements ConstraintValidator<DateIsAfterNow, OffsetDateTime> {

    @Override
    public void initialize(DateIsAfterNow constraintAnnotation) {
    }

    @Override
    public boolean isValid(OffsetDateTime offsetDateTime, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return OffsetDateTime.now(ZoneId.systemDefault()).isBefore(offsetDateTime);
        } catch (NullPointerException exception) {
            return false;
        }
    }

}
