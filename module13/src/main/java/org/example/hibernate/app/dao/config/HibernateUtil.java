package org.example.hibernate.app.dao.config;

import lombok.Getter;
import org.example.hibernate.app.dao.model.Category;
import org.example.hibernate.app.dao.model.Customer;
import org.example.hibernate.app.dao.model.CustomerDetails;
import org.example.hibernate.app.dao.model.Order;
import org.example.hibernate.app.dao.model.OrderItem;
import org.example.hibernate.app.dao.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(CustomerDetails.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(OrderItem.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }
}
