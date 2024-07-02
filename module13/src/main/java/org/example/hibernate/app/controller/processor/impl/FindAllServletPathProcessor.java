package org.example.hibernate.app.controller.processor.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hibernate.app.config.TemplateConfig;
import org.example.hibernate.app.controller.processor.PathType;
import org.example.hibernate.app.controller.processor.ServletPathProcessor;
import org.example.hibernate.app.dao.CustomerDao;
import org.example.hibernate.app.dao.model.Customer;
import org.thymeleaf.context.Context;

import java.util.List;

public class FindAllServletPathProcessor implements ServletPathProcessor {
    private final CustomerDao customerDao = CustomerDao.getInstance();
    private final TemplateConfig templateConfig = TemplateConfig.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Context context = new Context();
        Integer max = parseIntOrDefault(req.getParameter("max"));
        Integer offset = parseIntOrDefault(req.getParameter("offset"));
        List<Customer> allCustomers = customerDao.findAll(max, offset);
        context.setVariable("action", "findAll");
        context.setVariable("customers", allCustomers);
        context.setVariable("max", max);
        context.setVariable("offset", offset);
        templateConfig.process("index", context, resp);
    }

    @Override
    public PathType getProcessorType() {
        return PathType.FIND_ALL;
    }

    private int parseIntOrDefault(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return 0;
        }
    }
}
