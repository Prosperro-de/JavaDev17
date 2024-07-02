package org.example.hibernate.app.controller.processor.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hibernate.app.config.TemplateConfig;
import org.example.hibernate.app.controller.processor.PathType;
import org.example.hibernate.app.controller.processor.ServletPathProcessor;
import org.example.hibernate.app.dao.CustomerDao;
import org.thymeleaf.context.Context;

import java.io.IOException;

public class CreateCustomerServletPathProcessor implements ServletPathProcessor {
    private final CustomerDao customerDao = CustomerDao.getInstance();
    private final TemplateConfig templateConfig = TemplateConfig.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Context context = new Context();
        Long customerId = customerDao.create(req.getParameterMap());
        context.setVariable("action", "showCustomerId");
        context.setVariable("customerId", customerId);
        templateConfig.process("index", context, resp);
    }

    @Override
    public PathType getProcessorType() {
        return PathType.CREATE_CUSTOMER;
    }
}
