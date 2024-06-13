package org.example.servlet.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.servlet.app.config.TemplateConfig;

import org.thymeleaf.context.*;

import java.io.IOException;

//@WebServlet(name = "MyServlet", urlPatterns = {"/", "/storeUser"})
public class MyServlet extends HttpServlet {
    private TemplateConfig templateConfig;

    @Override
    public void init() throws ServletException {
        templateConfig = new TemplateConfig();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        Context context = new Context();
        context.setVariable("username", username);
        templateConfig.process("indexDemo", context, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");

        if (username != null && !username.trim().isEmpty()) {
            req.getSession().setAttribute("username", username);
        }

        resp.sendRedirect("/");
    }
}
