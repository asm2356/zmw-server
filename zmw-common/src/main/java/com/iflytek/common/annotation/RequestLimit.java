package com.iflytek.common.annotation;

import java.lang.annotation.*;

/**
 * @author AZhao
 * 方法请求次数限制
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLimit {
    long count() default Integer.MAX_VALUE;
    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 时间段默认为一分钟 单位秒
     */
    long time() default 60;
}
