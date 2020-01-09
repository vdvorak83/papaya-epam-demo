package com.epam.papaya.rrmistarter.annotations;

import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Evgeny Borisov
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AdaptToRemote {
    String serviceName();
    String endpoint();
    RequestMethod method() default RequestMethod.GET;
    String dtoName();
}
