package com.thoughtworks.gtb.basicquiz.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class BytesSizeValidater implements ConstraintValidator<BtyesSize, String> {
    private int min;
    private int max;
    @Override
    public void initialize(BtyesSize constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }
        int length = value.getBytes().length;
        return length >= min && length <= max;
    }
}
