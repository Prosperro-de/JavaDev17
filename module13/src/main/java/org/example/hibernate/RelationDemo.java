package org.example.hibernate;

import jakarta.transaction.Transactional;
import jdk.dynalink.linker.LinkerServices;
import org.example.hibernate.app.dao.CustomerDao;
import org.example.hibernate.app.dao.model.Customer;
import org.example.hibernate.app.dao.model.CustomerDetails;
import org.example.hibernate.app.dao.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class RelationDemo {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
//        Customer newCustomer = setupCustomer();
//
//        Customer customer = customerDao.create(newCustomer);
//        System.out.println("customer = " + customer);

        Customer firstCustomer = customerDao.findById(1L);
//        System.out.println("firstCustomer = " + firstCustomer);
        String s = "";

        // N + 1 problem

//        var customers = customerDao.findAll(100, 0);
//        System.out.println("customers = " + customers);

//        Order order = Order.builder()
//                .orderDate(LocalDate.now())
//                .totalPrice(BigDecimal.TEN)
//                .build();
//
//        customerDao.saveOrderForCustomer(1L, order);


    }

    private static Customer setupCustomer() {
        Customer newCustomer = Customer.builder()
                .firstName("Hiber")
                .lastName("Nate")
                .email("redHat" + new Random().nextInt(10000) + "@mail.com")
                .phoneNumber(new Random().nextInt(100000) + "")
                .postCode("555-555")
                .build();
        CustomerDetails details = CustomerDetails.builder()
                .dateOfBirth(LocalDate.now())
                .loyaltyPoints(100)
                .customer(newCustomer)
                .build();
        newCustomer.setCustomerDetails(details);
        return newCustomer;
    }
}
