package org.example.hibernate.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hibernate.app.controller.processor.PathType;
import org.example.hibernate.app.controller.processor.ServletPathProcessor;
import org.example.hibernate.app.controller.processor.ServletPathProcessorFactory;
import org.example.hibernate.app.dao.model.Customer;
import org.example.hibernate.app.dao.CustomerDao;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/", "/createCustomerForm", "/findCustomerByIdForm", "/findAllCustomers",
        "/createCustomer", "/findById", "/deleteCustomerByIdForm", "/deleteById", "/findCustomerByEmailForm"})
public class CustomerServlet extends HttpServlet {
    private final ServletPathProcessorFactory pathProcessorFactory = ServletPathProcessorFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        ServletPathProcessor processor = pathProcessorFactory.getProcessor(PathType.pathOf(servletPath));
        try {
            processor.process(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String servletPath = req.getServletPath();
        ServletPathProcessor processor = pathProcessorFactory.getProcessor(PathType.pathOf(servletPath));
        try {
            processor.process(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
