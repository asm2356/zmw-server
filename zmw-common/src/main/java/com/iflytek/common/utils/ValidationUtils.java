package com.iflytek.common.utils;

import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.exception.MyValidationException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtils {
    /**
     * 使用hibernate的注解来进行验证
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 校验
     * @param obj
     * @param <T>
     */
    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        if (constraintViolations.size() > 0) {
            throw new MyValidationException(ResultCode.Validation_Exception.getCode(), constraintViolations.iterator().next().getMessage());
        }
    }
}
