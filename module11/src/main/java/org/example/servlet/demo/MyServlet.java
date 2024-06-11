package org.example.servlet.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.*;
import org.thymeleaf.context.*;

import java.io.IOException;

@WebServlet(name = "MyServlet", urlPatterns = {"/", "/storeUser"})
public class MyServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() {
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());
//        templateResolver.setPrefix("/WEB-INF/templates/");
//        templateResolver.setSuffix(".html");
//        templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        HttpSession session = req.getSession(false);
//        String username = (session != null) ? (String) session.getAttribute("username") : null;
//
//        WebContext context = new WebContext(req, resp, getServletContext(), req.getLocale());
//        context.setVariable("username", username);
//        templateEngine.process("index", context, resp.getWriter());
        HttpSession session = req.getSession(true);
        String username = (session != null) ? (String) session.getAttribute("username") : null;
        req.setAttribute("username", username);
        req.getRequestDispatcher("/index.html").forward(req, resp);

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
