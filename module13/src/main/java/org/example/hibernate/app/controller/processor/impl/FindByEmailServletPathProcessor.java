package org.example.hibernate.app.controller.processor.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hibernate.app.config.TemplateConfig;
import org.example.hibernate.app.controller.processor.PathType;
import org.example.hibernate.app.controller.processor.ServletPathProcessor;
import org.example.hibernate.app.dao.CustomerDao;
import org.example.hibernate.app.dao.model.Customer;
import org.thymeleaf.context.Context;

public class FindByEmailServletPathProcessor implements ServletPathProcessor {
    private final CustomerDao customerDao = CustomerDao.getInstance();
    private final TemplateConfig templateConfig = TemplateConfig.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Context context = new Context();
        String email = req.getParameter("email");
        Customer customer = customerDao.findByEmail(email);
        context.setVariable("action", "showCustomer");
        context.setVariable("customer", customer);
        templateConfig.process("index", context, resp);
    }

    @Override
    public PathType getProcessorType() {
        return PathType.FIND_BY_EMAIL;
    }
}
