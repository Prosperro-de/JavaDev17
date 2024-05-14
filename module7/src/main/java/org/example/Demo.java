package org.example;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
//        Customer customer = customerService.registerNewCustomer();
//        System.out.println("customer = " + customer);
        Customer customerById = customerService.getCustomerById(5L);
        System.out.println("customerById = " + customerById);
        List<Customer> customers = List.of(
                Customer.builder()
                        .lastName("lastName2")
                        .email("email11")
                        .build(),
                Customer.builder()
                        .lastName("lastName3")
                        .email("email13")
                        .build(),
                Customer.builder()
                        .lastName("lastName4")
                        .email("email12")
                        .build()
        );
        customerService.saveCustomers(customers);
    }
}
