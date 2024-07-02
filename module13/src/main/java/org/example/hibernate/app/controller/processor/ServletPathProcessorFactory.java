package org.example.hibernate.app.controller.processor;

import org.example.hibernate.app.controller.processor.impl.CreateCustomerFormServletPathProcessor;
import org.example.hibernate.app.controller.processor.impl.CreateCustomerServletPathProcessor;
import org.example.hibernate.app.controller.processor.impl.DeleteByIdServletPathProcessor;
import org.example.hibernate.app.controller.processor.impl.DeleteCustomerFormServletPathProcessor;
import org.example.hibernate.app.controller.processor.impl.FindAllServletPathProcessor;
import org.example.hibernate.app.controller.processor.impl.FindByEmailFormServletPathProcessor;
import org.example.hibernate.app.controller.processor.impl.FindByEmailServletPathProcessor;
import org.example.hibernate.app.controller.processor.impl.FindByIdFormServletPathProcessor;
import org.example.hibernate.app.controller.processor.impl.FindByIdServletPathProcessor;
import org.example.hibernate.app.controller.processor.impl.HomePageServletPathProcessor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServletPathProcessorFactory {
    private static ServletPathProcessorFactory INSTANCE;

    Map<PathType, ServletPathProcessor> processorMap;

    private ServletPathProcessorFactory() {
        processorMap = Stream.of(
                new CreateCustomerFormServletPathProcessor(),
                        new CreateCustomerServletPathProcessor(),
                        new DeleteByIdServletPathProcessor(),
                        new DeleteCustomerFormServletPathProcessor(),
                        new FindAllServletPathProcessor(),
                        new FindByEmailFormServletPathProcessor(),
                        new FindByEmailServletPathProcessor(),
                        new FindByIdServletPathProcessor(),
                        new FindByIdFormServletPathProcessor(),
                        new HomePageServletPathProcessor())
                .collect(Collectors.toMap(
                        ServletPathProcessor::getProcessorType, Function.identity()));
    }

    public ServletPathProcessor getProcessor(PathType processorType) {
        return processorMap.get(processorType);
    }

    public static ServletPathProcessorFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServletPathProcessorFactory();
        }
        return INSTANCE;
    }
}
