package org.example.module17.app.service;

import org.example.module17.app.model.Customer;
import org.example.module17.app.model.dto.CustomerResponse;
import org.example.module17.app.exception.BadRequestException;
import org.example.module17.app.model.dto.request.CustomerUpdateRequest;
import org.example.module17.app.repository.CustomerRepository;
import org.example.module17.app.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
        return customerMapper.toCustomerResponse(
                customerRepository.findById(id)
                        .orElseThrow(BadRequestException::new)
        );
    }

    public CustomerResponse getByEmail(String email) {
        return customerMapper.toCustomerResponse(customerRepository.getCustomersByEmail(email)
                .orElseThrow(BadRequestException::new));
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
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
