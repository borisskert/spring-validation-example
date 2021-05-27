package de.borisskert.spring.springvalidationexample.validator;

import de.borisskert.spring.springvalidationexample.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UnoccupiedUsernameValidator implements ConstraintValidator<UnoccupiedUsername, String> {

    private final UserService userService;

    @Autowired
    public UnoccupiedUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username == null || username.isEmpty()) {
            return true;
        }

        return userService.getUserByName(username)
                .isEmpty();
    }
}
