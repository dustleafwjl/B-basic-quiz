package com.thoughtworks.gtb.basicquiz.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = BytesSizeValidater.class)
public @interface BtyesSize {
    String message() default "格式不正确";
    int min() default 0;
    int max() default 1024;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
