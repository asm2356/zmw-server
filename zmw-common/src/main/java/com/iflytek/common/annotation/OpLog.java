package com.iflytek.common.annotation;

import java.lang.annotation.*;
/**
 * @author AZhao
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpLog {
    String describe() default "";
}
