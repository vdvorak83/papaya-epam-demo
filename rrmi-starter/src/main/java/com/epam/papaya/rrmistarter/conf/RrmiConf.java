package com.epam.papaya.rrmistarter.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Evgeny Borisov
 */
@Configuration
public class RrmiConf {
    @ConditionalOnMissingBean
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
