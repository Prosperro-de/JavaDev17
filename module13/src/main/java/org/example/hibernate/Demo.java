package org.example.hibernate;


import org.example.hibernate.app.controller.processor.PathType;
import org.example.hibernate.app.dao.config.HibernateUtil;
import org.example.hibernate.app.dao.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Random;

public class Demo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
//            Customer customer = session.find(Customer.class, 1L);

//            Customer newCustomer = Customer.builder()
//                    .firstName("Hiber")
//                    .lastName("Nate")
//                    .email("redHat" + new Random().nextInt(10000) + "@mail.com")
//                    .phoneNumber(new Random().nextInt(100000) + "")
//                    .postCode("555-555")
//                    .build();
//            session.persist(newCustomer);

//            Customer customer = session.find(Customer.class, 49L);
//
//            System.out.println("customer = " + customer);
//
//            customer.setLastName("Spring3");
//            session.detach(customer);
//            session.merge(customer);

//            Customer customer2 = session.find(Customer.class, 49L);
//            System.out.println("customer2 = " + customer2);
//
//
//            System.out.println("some another process");

//            Customer customer = session.find(Customer.class, 49L);
//            System.out.println("customer = " + customer);
//
//            session.remove(customer);
//
//            session.persist(customer);
//
//            System.out.println("customer = " + customer);
//
//            transaction.commit();


                System.out.println(PathType.valueOf("/findByEmail"));

        }
    }
}
