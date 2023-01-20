package com.nguyen1207.vishbe.validators;

import com.nguyen1207.vishbe.annotations.ValidateVietnamesePhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VietnamesePhoneValidator implements ConstraintValidator<ValidateVietnamesePhone, String> {
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (phone == null) return true;

        String vietnamesePhoneRegex = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b";

        return phone.matches(vietnamesePhoneRegex);
    }
}
