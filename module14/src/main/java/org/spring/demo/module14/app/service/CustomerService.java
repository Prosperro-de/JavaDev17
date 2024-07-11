package org.spring.demo.module14.app.service;

import lombok.RequiredArgsConstructor;
import org.spring.demo.module14.app.dao.CustomerDao;
import org.spring.demo.module14.app.dao.model.Customer;
import org.spring.demo.module14.app.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDao customerDao;

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
}
