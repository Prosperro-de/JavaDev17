package org.example.module16.app.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.module16.app.model.Customer;
import org.example.module16.app.model.dto.CustomerResponse;
import org.example.module16.app.exception.BadRequestException;
import org.example.module16.app.model.dto.request.CustomerUpdateRequest;
import org.example.module16.app.repository.CustomerRepository;
import org.example.module16.app.mapper.CustomerMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {

    private final RestTemplate client;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(@Qualifier(value = "paymentClient") RestTemplate client, CustomerRepository customerRepository,
                           CustomerMapper customerMapper) {
        this.client = client;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerResponse getById(Long id) {
//        return CustomerMapper.toDto(customerDao.findById(id).orElseThrow(BadRequestException::new));
        return customerMapper.toCustomerResponse(
                customerRepository.findById(id)
                        .orElseThrow(BadRequestException::new)
        );
    }

    public CustomerResponse getByEmail(String email) {
//        return customerDao.findByEmail(email).orElseThrow(BadRequestException::new);
        return customerMapper.toCustomerResponse(customerRepository.getCustomersByEmail(email)
                .orElseThrow(BadRequestException::new));
    }

//    @EventListener(ApplicationReadyEvent.class)
    public void getByEmail() {
//        return customerDao.findByEmail(email).orElseThrow(BadRequestException::new);
        customerRepository.getCustomersByEmailOrFirstName("postman@example.com", "Taras")
                .forEach(System.out::println);
    }


    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @EventListener(ApplicationReadyEvent.class)
    public void smth() {
        customerRepository.findAllOrderById(1L).forEach(System.out::println);
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public CustomerResponse update(Long id, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setPostCode(request.getPostCode());
        return customerMapper.toCustomerResponse(customer);
    }

    public void placeOrder() {
//        restTemplate.pos
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        return customerRepository.findAll(getCustomerByFirstName(firstName));
    }

    private Specification<Customer> getCustomerByFirstName(String firstName) {
        return (root, query, builder) -> Optional.ofNullable(firstName)
                .map(name -> builder.equal(root.get("firstName"), firstName))
                .orElseGet(builder::conjunction);
    }
}
