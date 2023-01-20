package com.nguyen1207.vishbe.annotations;

import com.nguyen1207.vishbe.validators.ProvinceValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProvinceValidator.class)
public @interface ValidateProvince {
    String message() default "Invalid province";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
