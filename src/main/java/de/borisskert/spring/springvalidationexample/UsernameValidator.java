package de.borisskert.spring.springvalidationexample;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z]{8,12}$");


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        return USERNAME_PATTERN.matcher(s).matches();
    }
}
