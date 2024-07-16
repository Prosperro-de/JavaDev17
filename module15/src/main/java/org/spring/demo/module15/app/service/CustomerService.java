package org.spring.demo.module15.app.service;

import lombok.RequiredArgsConstructor;
import org.spring.demo.module15.app.dao.CustomerDao;
import org.spring.demo.module15.app.dao.model.Customer;
import org.spring.demo.module15.app.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;
    private final RestTemplate client;
    private int timeout;

    public CustomerService(@Qualifier(value = "paymentClient") RestTemplate client,
                           CustomerDao customerDao,
                           @Value("${timeout}") int timeout) {
        this.client = client;
        this.customerDao = customerDao;
        this.timeout = timeout;
    }

    public Customer getById(Long id) {
        return customerDao.findById(id).orElseThrow(BadRequestException::new);
    }

    public Customer getByEmail(String email) {
        return customerDao.findByEmail(email).orElseThrow(BadRequestException::new);
    }

    public void deleteById(Long id) {
        customerDao.delete(id);
    }

    public List<Customer> findAll(Integer max, Integer offset) {
        return customerDao.findAll(max, offset).orElseThrow(BadRequestException::new);
    }

    public Customer create(Customer customer) {
        return customerDao.create(customer);
    }

    public void placeOrder() {
//        restTemplate.pos
    }
}
