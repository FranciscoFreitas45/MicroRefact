package com.project.stockexchangeappbackend.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = DateIsAfterNowValidation.class)
@Documented
public @interface DateIsAfterNow {

    String message() default "Given datetime must be later than current datetime.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
