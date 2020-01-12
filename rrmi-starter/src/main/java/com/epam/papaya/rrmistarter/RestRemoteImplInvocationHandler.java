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

        String serviceName = "http://localhost:8080";
//        String serviceName = annotation.serviceName();
        String endpoint = annotation.endpoint().isEmpty() ? getDefaultEndpoint(method) : annotation.endpoint();
        HttpMethod httpMethod = getDefaultHttpMethod(method,annotation);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(args[0], method.getReturnType());
        HttpMessageConverterExtractor<?> responseExtractor = new HttpMessageConverterExtractor(method.getReturnType(), restTemplate.getMessageConverters());
        return restTemplate.execute(serviceName + endpoint, httpMethod, requestCallback, responseExtractor);
//
    }

    private HttpMethod getDefaultHttpMethod(Method method, AdaptToRemote annotation) {
        if (method.getParameterTypes().length == 0 && annotation.method()==HttpMethod.POST) {
            return HttpMethod.GET;
        } else {
            return annotation.method();
        }
    }

    private String getDefaultEndpoint(Method method) {
        return ControllerRegistry.GENERATED_CONTROLLER_PREFIX + method.getName();
    }
}




