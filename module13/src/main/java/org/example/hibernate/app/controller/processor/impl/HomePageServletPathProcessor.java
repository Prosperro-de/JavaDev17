package org.example.hibernate.app.controller.processor.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hibernate.app.config.TemplateConfig;
import org.example.hibernate.app.controller.processor.PathType;
import org.example.hibernate.app.controller.processor.ServletPathProcessor;
import org.thymeleaf.context.Context;

public class HomePageServletPathProcessor implements ServletPathProcessor {
    private final TemplateConfig templateConfig = TemplateConfig.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Context context = new Context();
        templateConfig.process("index", context, resp);
    }

    @Override
    public PathType getProcessorType() {
        return PathType.HOME_PAGE;
    }
}
