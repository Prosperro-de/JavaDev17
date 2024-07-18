package org.spring.demo.module15.app.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.spring.demo.module15.app.dao.model.Customer;
import org.spring.demo.module15.app.dao.model.dto.CustomerResponse;
import org.spring.demo.module15.app.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/createCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/findById/{id}")
    public CustomerResponse findById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @GetMapping("/findByEmail")
    public Customer findByEmail(@RequestParam String email) {
        return customerService.getByEmail(email);
    }

    @GetMapping("/deleteById/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            //Bad practice
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findAllCustomers")
    public List<Customer> findAll(@RequestParam(required = false) Integer max,
                                  @RequestParam(required = false) Integer offset) {
        return customerService.findAll(max, offset);
    }
}
