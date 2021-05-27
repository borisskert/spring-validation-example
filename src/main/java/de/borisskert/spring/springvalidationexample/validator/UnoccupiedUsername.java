package de.borisskert.spring.springvalidationexample.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UnoccupiedUsernameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnoccupiedUsername {

    String message() default "{de.borisskert.spring.springvalidationexample.validator.UnoccupiedUsername.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
