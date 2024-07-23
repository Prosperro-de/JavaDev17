package org.spring.demo.module15.app.service;

import lombok.RequiredArgsConstructor;
import org.spring.demo.module15.app.dao.CustomerDao;
import org.spring.demo.module15.app.dao.model.Customer;
import org.spring.demo.module15.app.dao.model.dto.CustomerResponse;
import org.spring.demo.module15.app.exception.BadRequestException;
import org.spring.demo.module15.app.util.mapper.CustomerMapper;
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
    private final CustomerMapper customerMapper;
    private int timeout;

    public CustomerService(@Qualifier(value = "paymentClient") RestTemplate client,
                           CustomerDao customerDao, CustomerMapper customerMapper,
                           @Value("${timeout}") int timeout) {
        this.client = client;
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
        this.timeout = timeout;
    }

    public CustomerResponse getById(Long id) {
//        return CustomerMapper.toDto(customerDao.findById(id).orElseThrow(BadRequestException::new));
        return customerMapper.toCustomerResponse(
                customerDao.findById(id)
                        .orElseThrow(BadRequestException::new)
        );
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
