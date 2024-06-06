package org.example.servlet.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.app.exception.BadRequestException;
import org.example.servlet.app.service.CustomerService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/customers")
public class CustomerController extends HttpServlet {
    private CustomerService customerService = new CustomerService();

    public CustomerController() throws SQLException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getParameterMap()
//                .forEach((k, v) -> System.out.println("Key: " + k + " Value: " + Arrays.toString(v)));
        try {
//            customerService.create(req.getParameterMap());
            customerService.create(req.getReader());
            resp.setStatus(201);
        } catch (BadRequestException ex) {
            resp.setStatus(400);
        } catch (Exception e) {
            resp.setStatus(500);
        }

    }
}
