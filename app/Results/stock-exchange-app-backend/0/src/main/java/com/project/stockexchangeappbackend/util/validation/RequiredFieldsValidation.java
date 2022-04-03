package com.project.stockexchangeappbackend.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RequiredFieldsValidation implements ConstraintValidator<RequiredFields, Object> {

    private List<String> requiredFields;

    @Override
    public void initialize(RequiredFields constraintAnnotation) {
        requiredFields = Arrays.asList(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return requiredFields.stream().allMatch(field -> {
            try {
                return Optional.ofNullable(
                        new PropertyDescriptor(field, o.getClass()).getReadMethod().invoke(o))
                        .isPresent();
            }  catch (IntrospectionException | IllegalAccessException | InvocationTargetException | NullPointerException e) {
                return false;
            }
        });
    }

}