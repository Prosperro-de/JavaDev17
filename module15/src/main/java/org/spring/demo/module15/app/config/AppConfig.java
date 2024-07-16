package org.spring.demo.module15.app.config;

import org.hibernate.SessionFactory;
import org.spring.demo.module15.app.dao.model.Category;
import org.spring.demo.module15.app.dao.model.Customer;
import org.spring.demo.module15.app.dao.model.CustomerDetails;
import org.spring.demo.module15.app.dao.model.Order;
import org.spring.demo.module15.app.dao.model.OrderItem;
import org.spring.demo.module15.app.dao.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Configuration
public class AppConfig {

    @Bean()
    public SessionFactory getSessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(CustomerDetails.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(OrderItem.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

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
