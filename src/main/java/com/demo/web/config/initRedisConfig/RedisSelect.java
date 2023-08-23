package com.demo.web.config.initRedisConfig;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解，用于切换同一redis数据源下的不同db index
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisSelect {
    /**
     *默认连接db10
     * @return
     */
    String instance() default "back";
}