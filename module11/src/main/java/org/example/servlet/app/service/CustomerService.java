package org.example.servlet.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.servlet.app.dao.GenericDao;
import org.example.servlet.app.dao.model.Customer;
import org.example.servlet.app.exception.BadRequestException;
import org.example.servlet.app.util.RequestToEntityMapper;

import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private GenericDao dao = new GenericDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    public CustomerService() throws SQLException {
    }

    public Customer findById(Long id) {
        return dao.findById(id, Customer.class);
    }

    public List<Customer> findAll() {
        return dao.findAll(Customer.class);
    }


    public Long create(Map<String, String[]> params) throws Exception {
        Customer customer = RequestToEntityMapper.mapToEntity(params, Customer.class);
        if (customer.getEmail().isEmpty()) {
            throw new BadRequestException();
        }
        return dao.save(customer);
    }

    public void delete(Long id) throws SQLException {
        Customer byId = dao.findById(id, Customer.class);
        dao.delete(byId);
    }

//    public void create(BufferedReader payload) throws Exception {
//        Customer customer = objectMapper.readValue(payload, Customer.class);
//        dao.save(customer);
//        Customer customer = RequestToEntityMapper.mapToEntity(params, Customer.class);
//        if (customer.getEmail().isEmpty()) {
//            throw new BadRequestException();
//        }
//        dao.save(customer);
//    }
}
