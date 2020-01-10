package com.epam.papaya.rrmistarter;

import com.epam.papaya.rrmistarter.annotations.AdaptToRemote;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Evgeny Borisov
 */
@RequiredArgsConstructor
public class RestRemoteImplInvocationHandler implements InvocationHandler {


    private final ConfigurableApplicationContext context;
    private final Class<? extends Adapter> adapterIfc;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        if (method.getName().equals("equals")) {
            return true;
        }
        AdaptToRemote annotation = adapterIfc.getMethod(method.getName(), method.getParameterTypes()).getAnnotation(AdaptToRemote.class);

        String serviceName = "http://localhost:8080/";
//        String serviceName = annotation.serviceName();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(args[0], method.getReturnType());
        HttpMessageConverterExtractor<?> responseExtractor = new HttpMessageConverterExtractor(method.getReturnType(), restTemplate.getMessageConverters());
        return restTemplate.execute(serviceName + annotation.endpoint(), annotation.method(), requestCallback, responseExtractor);
//
    }
}




