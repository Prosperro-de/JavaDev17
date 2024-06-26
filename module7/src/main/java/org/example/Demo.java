package org.example;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
//        Customer customer = customerService.registerNewCustomer();
//        System.out.println("customer = " + customer);
//        Customer customerById = customerService.getCustomerById(5L);
//        System.out.println("customerById = " + customerById);
        List<Customer> customers = List.of(
                Customer.builder()
                        .lastName("lastName2")
                        .email("email1234")
                        .build(),
                Customer.builder()
                        .lastName("lastName3")
                        .email("email42313")
                        .build(),
                Customer.builder()
                        .lastName("lastName4")
                        .email("email12234")
                        .build()
        );
        customerService.saveCustomers(customers);
    }
}
