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
    String endpoint();
    HttpMethod method() default HttpMethod.GET;
   // todo Why do we need this? String dtoName();
}
