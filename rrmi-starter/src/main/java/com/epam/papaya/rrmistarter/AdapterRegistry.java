package com.epam.papaya.rrmistarter;

import com.epam.papaya.rrmistarter.annotations.AdaptTo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;

/**
 * @author Evgeny Borisov
 */
public class AdapterRegistry implements ApplicationContextInitializer {
    private ObjectMapper mapper = new ObjectMapper();

    {
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_ABSENT);
    }


    @Override
    public void initialize(ConfigurableApplicationContext context) {


        var scanner = new Reflections("com.epam.papaya");
        Set<Class<? extends Adapter>> set = scanner.getSubTypesOf(Adapter.class);
        set.stream().filter(Class::isInterface).forEach(adapterIfc->{
            Object proxyAdapter = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{adapterIfc}, (proxy, method, args) -> {

                    AdaptTo annotation = adapterIfc.getMethod(method.getName(), method.getParameterTypes()).getAnnotation(AdaptTo.class);

                    Object real = context.getBean(annotation.ifc());
                    Method realMethod = Arrays.stream(real.getClass().getMethods()).filter(method1 -> method1.getName().equals(method.getName())).findAny().get();


                    var inputType = realMethod.getParameterTypes()[0];

                    Object input = mapper.convertValue(args[0], inputType);
                    Object retVal = realMethod.invoke(real, input);
                    Object convertedRetVal = mapper.convertValue(retVal, method.getReturnType());
                    return convertedRetVal;

               



            });


            Arrays.stream(proxyAdapter.getClass().getInterfaces()).forEach(System.out::println);
            context.getBeanFactory().registerSingleton(adapterIfc.getSimpleName(), proxyAdapter);
        });

    }
}
