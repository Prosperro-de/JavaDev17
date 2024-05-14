package org.example;

import lombok.SneakyThrows;
import org.example.prepared.CustomerDaoV2;
import org.example.statement.CustomerDao;

import java.util.List;
import java.util.Scanner;

public class CustomerService {
//    private CustomerDao customerDao = new CustomerDao();
    private CustomerDaoV2 customerDao = new CustomerDaoV2();
    private Scanner scanner = new Scanner(System.in);

    @SneakyThrows
    public Customer registerNewCustomer() {
        System.out.println("Please enter your name");
        String name = scanner.nextLine();
        System.out.println("Please enter your last name");
        String lastName = scanner.nextLine();
        System.out.println("Please enter your email name");
        String email = scanner.nextLine();
        Customer newCustomer = Customer.builder()
                .firstName(name)
                .lastName(lastName)
                .email(email)
                .build();
        customerDao.save(newCustomer);
        return newCustomer;
    }

    @SneakyThrows
    public Customer getCustomerById(Long id) {
        return customerDao.findById(id);
    }

    @SneakyThrows
    public void saveCustomers(List<Customer> customers) {
        customerDao.saveCustomers(customers);
    }
}
