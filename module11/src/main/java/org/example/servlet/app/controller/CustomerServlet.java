package org.example.servlet.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.app.config.TemplateConfig;
import org.example.servlet.app.dao.model.Customer;
import org.example.servlet.app.service.CustomerService;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/", "/createCustomerForm", "/findCustomerByIdForm", "/findAllCustomers",
        "/createCustomer", "/findById", "/deleteCustomerByIdForm", "/deleteById"})
public class CustomerServlet extends HttpServlet {
    private TemplateConfig templateConfig = new TemplateConfig();
    private CustomerService customerService = new CustomerService();

    public CustomerServlet() throws ServletException, SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        Context context = new Context();

        switch (servletPath) {
            case "/createCustomerForm" -> {
                context.setVariable("action", "create");
                templateConfig.process("index", context, resp);
            }
            case "/findCustomerByIdForm" -> {
                context.setVariable("action", "findById");
                templateConfig.process("index", context, resp);
            }
            case "/deleteCustomerByIdForm" -> {
                context.setVariable("action", "delete");
                templateConfig.process("index", context, resp);
            }
            case "/findById" -> {
                String id = req.getParameter("id");
                Customer customer = customerService.findById(Long.parseLong(id));
                context.setVariable("action", "showCustomer");
                context.setVariable("customer", customer);
                templateConfig.process("index", context, resp);
            }
            case "/findAllCustomers" -> {
                List<Customer> allCustomers = customerService.findAll();
                context.setVariable("action", "findAll");
                context.setVariable("customers", allCustomers);
                templateConfig.process("index", context, resp);
            }
            case "/deleteById" -> {
                String id = req.getParameter("id");
                try {
                    customerService.delete(Long.parseLong(id));
                    templateConfig.process("index", context, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            default -> templateConfig.process("index", context, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        Context context = new Context();
        if (servletPath.equals("/createCustomer")) {
            try {
                Long customerId = customerService.create(req.getParameterMap());
                context.setVariable("action", "showCustomerId");
                context.setVariable("customerId", customerId);
                templateConfig.process("index", context, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
