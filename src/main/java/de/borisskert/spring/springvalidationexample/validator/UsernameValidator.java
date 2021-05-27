package de.borisskert.spring.springvalidationexample.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z]{8,12}$");


    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username == null || username.isEmpty()) {
            return true;
        }

        return USERNAME_PATTERN.matcher(username).matches();
    }
}
