package com.epam.papaya.rrmistarter;

import com.epam.papaya.rrmistarter.annotations.AdaptTo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Evgeny Borisov
 */
@RequiredArgsConstructor
public class LocalImplInvocationHandler implements InvocationHandler {

    private final ConfigurableApplicationContext context;
    private final Class<? extends Adapter> adapterIfc;

    private ObjectMapper mapper = new ObjectMapper();
    {
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_ABSENT);
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //todo handle Object methods, because they don't exist in interface
        if (method.getName().equals("equals")) {
            return true;
        }
        AdaptTo annotation = adapterIfc.getMethod(method.getName(), method.getParameterTypes()).getAnnotation(AdaptTo.class);

        Object real = context.getBean(annotation.ifc());
        Method realMethod = Arrays.stream(real.getClass().getMethods()).filter(method1 -> method1.getName().equals(method.getName())).findAny().get();
        //todo improve performance - real method must be in some hashmap

        var inputType = realMethod.getParameterTypes()[0];

        Object input = mapper.convertValue(args[0], inputType);
        Object retVal = realMethod.invoke(real, input);
        Object convertedRetVal = mapper.convertValue(retVal, method.getReturnType());
        return convertedRetVal;


    }
}
