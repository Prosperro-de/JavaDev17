package org.spring.demo.module14.app.config;

import org.hibernate.SessionFactory;
import org.spring.demo.module14.app.dao.model.Category;
import org.spring.demo.module14.app.dao.model.Customer;
import org.spring.demo.module14.app.dao.model.CustomerDetails;
import org.spring.demo.module14.app.dao.model.Order;
import org.spring.demo.module14.app.dao.model.OrderItem;
import org.spring.demo.module14.app.dao.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
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
}
