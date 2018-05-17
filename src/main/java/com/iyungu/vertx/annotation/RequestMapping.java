package com.iyungu.vertx.annotation;

import io.vertx.ext.web.Router;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: Created in 16:09 2018/4/18
 * @Modified By:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     * 访问路径
     *
     * @return
     */
    String value() default "";
    /**
     * 方法类型
     *
     * @return
     */
    String methodType() default "";

}