package org.example.hibernate.app.controller.processor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ServletPathProcessor {

    void process(HttpServletRequest req, HttpServletResponse resp) throws Exception;

    PathType getProcessorType();
}
