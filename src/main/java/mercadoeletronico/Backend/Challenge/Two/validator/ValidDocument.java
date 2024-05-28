package mercadoeletronico.Backend.Challenge.Two.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DocumentValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidDocument {
    String message() default "Document invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
