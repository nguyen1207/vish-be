package com.nguyen1207.vishbe.annotations;

import com.nguyen1207.vishbe.validators.VietnamesePhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = VietnamesePhoneValidator.class)
public @interface ValidateVietnamesePhone {
    String message() default "Invalid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
