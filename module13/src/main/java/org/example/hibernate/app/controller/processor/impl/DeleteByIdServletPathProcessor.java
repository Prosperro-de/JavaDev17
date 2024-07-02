package org.example.hibernate.app.controller.processor.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hibernate.app.config.TemplateConfig;
import org.example.hibernate.app.controller.processor.PathType;
import org.example.hibernate.app.controller.processor.ServletPathProcessor;
import org.example.hibernate.app.dao.CustomerDao;
import org.thymeleaf.context.Context;

public class DeleteByIdServletPathProcessor implements ServletPathProcessor {
    private final CustomerDao customerDao = CustomerDao.getInstance();
    private final TemplateConfig templateConfig = TemplateConfig.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Context context = new Context();
        String id = req.getParameter("id");
        customerDao.delete(Long.parseLong(id));
        templateConfig.process("index", context, resp);
    }

    @Override
    public PathType getProcessorType() {
        return PathType.DELETE_BY_ID;
    }
}
