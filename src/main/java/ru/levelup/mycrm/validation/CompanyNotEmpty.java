package ru.levelup.mycrm.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CompanyNotEmptyValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyNotEmpty {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "At least one company should be present";

}
