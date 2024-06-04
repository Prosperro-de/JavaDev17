package org.example.servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {

    public static final String NAME = "name";
    public static final String EMAIL = "email";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME);
        String email = req.getParameter(EMAIL);

        req.setAttribute(NAME, name);
        req.setAttribute(EMAIL, email);

        req.getRequestDispatcher("response.jsp").forward(req, resp);
    }
}
