package com.epam.papaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaymentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PaymentApplication.class, args);
        System.out.println(123);
    }

}
