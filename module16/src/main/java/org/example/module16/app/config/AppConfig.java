package org.example.module16.app.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Configuration
public class AppConfig {
    @Bean(name = "paymentClient")
//    @Primary
    public RestTemplate getPaymentClient(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8081/")
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Bean(name = "productClient")
    public RestTemplate getProductClient(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8082/")
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }
}
