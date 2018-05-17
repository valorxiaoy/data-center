package com.iyungu.vertx.annotation;

import java.lang.annotation.*;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: 11:14$ 2018/5/17$
 * @Modified By:
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface DateTimeFormat {
    String style() default "SS";

    DateTimeFormat.ISO iso() default DateTimeFormat.ISO.NONE;

    String pattern() default "";

    public static enum ISO {
        DATE,
        TIME,
        DATE_TIME,
        NONE;

        private ISO() {
        }
    }
}