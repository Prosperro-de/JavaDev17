package org.example.annotationdemo;

public class Demo {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        Customer customer = Customer.builder()
                .name("  Mykola   ")
                .lastName("Klushyn")
                .email("  mail@gmail.com  ")
                .build();
        customerService.printCustomerDetails(customer);
    }
}
