package com.iyungu.vertx.annotation;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

import java.lang.annotation.*;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: Created in 16:09 2018/4/18
 * @Modified By:
 */
@Target({ElementType.TYPE})// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Retention(RetentionPolicy.RUNTIME)//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface RestController {
    String value() default "";
}
