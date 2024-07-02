package org.example.hibernate;

import org.example.hibernate.app.dao.CustomerDao;
import org.example.hibernate.app.dao.model.Customer;
import org.example.hibernate.app.dao.model.CustomerDetails;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class RelationDemo {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
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

        Customer customer = customerDao.create(newCustomer);
        System.out.println("customer = " + customer);

    }
}
