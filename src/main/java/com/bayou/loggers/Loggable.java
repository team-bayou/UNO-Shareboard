package com.bayou.loggers;

import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by satyendra on 11/7/16.
 */
@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Loggable {
    LogLevel value() default LogLevel.DEBUG;

    boolean params() default true;

    boolean result() default true;
}
