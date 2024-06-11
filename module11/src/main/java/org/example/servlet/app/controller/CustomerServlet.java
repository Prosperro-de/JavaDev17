package org.example.servlet.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.app.dao.model.Customer;
import org.example.servlet.app.service.CustomerService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/findCustomer", "/findAll"})
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();

    public CustomerServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/findCustomer" -> findCustomer(req, resp);
            case "/findAll" -> findAll(req, resp);
            default -> resp.sendRedirect("index.html");
        }
    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = customerService.findAll();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("ourTemplate").forward(req, resp);
    }

    private void findCustomer(HttpServletRequest req, HttpServletResponse resp) {

    }
}
