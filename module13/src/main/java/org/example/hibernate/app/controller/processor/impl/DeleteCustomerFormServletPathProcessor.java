package org.example.hibernate.app.controller.processor.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hibernate.app.config.TemplateConfig;
import org.example.hibernate.app.controller.processor.PathType;
import org.example.hibernate.app.controller.processor.ServletPathProcessor;
import org.thymeleaf.context.Context;

public class DeleteCustomerFormServletPathProcessor implements ServletPathProcessor {
    TemplateConfig templateConfig = TemplateConfig.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Context context = new Context();
        context.setVariable("action", "delete");
        templateConfig.process("index", context, resp);
    }

    @Override
    public PathType getProcessorType() {
        return PathType.DELETE_CUSTOMER_FORM;
    }
}
