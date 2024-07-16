package org.spring.demo.module15.app.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.spring.demo.module15.app.dao.model.Customer;
import org.spring.demo.module15.app.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/createCustomer")
    public ModelAndView create(@RequestBody Customer customer) {
        ModelAndView modelAndView = new ModelAndView("index");
        Customer created = customerService.create(customer);
        modelAndView.addObject("action", "showCustomerId");
        modelAndView.addObject("customerId", created.getId());
        return modelAndView;
    }

    @GetMapping("/findById")
    public ModelAndView findById(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("index");
        Customer customer = customerService.getById(id);
        modelAndView.addObject("action", "showCustomer");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("/findByEmail")
    public ModelAndView findByEmail(@RequestParam String email) {
        ModelAndView modelAndView = new ModelAndView("index");
        Customer customer = customerService.getByEmail(email);
        modelAndView.addObject("action", "showCustomer");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("/deleteById")
    public ModelAndView delete(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("index");
        customerService.deleteById(id);
        return modelAndView;
    }

    @GetMapping("/findAllCustomers")
    public ModelAndView findAll(@RequestParam(required = false) Integer max,
                                @RequestParam(required = false) Integer offset) {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Customer> all = customerService.findAll(max, offset);
        modelAndView.addObject("action", "findAll");
        modelAndView.addObject("customers", all);
        modelAndView.addObject("max", max);
        modelAndView.addObject("offset", offset);
        return modelAndView;
    }
}
