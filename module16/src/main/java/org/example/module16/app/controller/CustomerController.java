package org.example.module16.app.controller;

import lombok.RequiredArgsConstructor;
import org.example.module16.app.model.Customer;
import org.example.module16.app.model.dto.CustomerResponse;
import org.example.module16.app.model.dto.request.CustomerUpdateRequest;
import org.example.module16.app.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @GetMapping
    public CustomerResponse findByEmail(@RequestParam String email) {
        return customerService.getByEmail(email);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest request) {
        return customerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            //Bad practice
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findAll")
    public List<Customer> findAll() {
        return customerService.findAll();
    }
}
