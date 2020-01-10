package com.epam.papaya.rrmistarter;

import com.epam.papaya.rrmistarter.annotations.AdaptTo;
import com.epam.papaya.rrmistarter.annotations.AdaptToRemote;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Evgeny Borisov
 */
public class AdapterRegistry implements ApplicationContextInitializer {

    private Map<String, Map<String, Method>> methodsContainer = new HashMap<>();
    private ConfigurableApplicationContext context;


    @Override
    public void initialize(ConfigurableApplicationContext context) {

        this.context = context;
        var scanner = new Reflections("com.epam.papaya");
        Set<Class<? extends Adapter>> set = scanner.getSubTypesOf(Adapter.class);
        set.stream()
                .filter(Class::isInterface)
                .filter(this::needsTobeGenerated)
                .forEach(adapterIfc -> {

                    Object proxyAdapter = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{adapterIfc}, getInvocationHandler(adapterIfc));


                    Arrays.stream(proxyAdapter.getClass().getInterfaces()).forEach(System.out::println);
                    context.getBeanFactory().registerSingleton(adapterIfc.getSimpleName(), proxyAdapter);
                });

    }

    private InvocationHandler getInvocationHandler(Class<? extends Adapter> adapterIfc) {
        if (Arrays.stream(adapterIfc.getMethods()).anyMatch(method -> method.isAnnotationPresent(AdaptTo.class))) {
            return new LocalImplInvocationHandler(context, adapterIfc);
        }
        return new RestRemoteImplInvocationHandler(context, adapterIfc);
    }

    private boolean needsTobeGenerated(Class<? extends Adapter> aClass) {
        return Arrays.stream(aClass.getMethods()).anyMatch(method -> method.isAnnotationPresent(AdaptToRemote.class) || method.isAnnotationPresent(AdaptTo.class));
    }
}
