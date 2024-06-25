package org.example.hibernate.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.hibernate.app.dao.model.Customer;
import org.example.hibernate.app.exception.BadRequestException;
import org.example.hibernate.app.util.RequestToEntityMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CustomerService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public CustomerService() throws SQLException {
    }

    public Customer findById(Long id) {
        return null;
    }

    public List<Customer> findAll() {
        return null;
    }


    public Long create(Map<String, String[]> params) throws Exception {
        Customer customer = RequestToEntityMapper.mapToEntity(params, Customer.class);
        if (customer.getEmail().isEmpty()) {
            throw new BadRequestException();
        }
        return null;
    }

    public void delete(Long id) throws SQLException {

    }
}
