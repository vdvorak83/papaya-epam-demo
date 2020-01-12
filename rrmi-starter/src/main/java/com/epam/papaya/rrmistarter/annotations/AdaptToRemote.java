package com.epam.papaya.rrmistarter.annotations;

import org.springframework.http.HttpMethod;
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
    String endpoint() default "";
    HttpMethod method() default HttpMethod.POST; // this default will be changed to GET automatically in case annotated method doesn't receive any parameters
   // todo Why do we need this? String dtoName();
}
