package org.example;

import org.example.context.ApplicationContext;
import org.example.context.ApplicationContextImpl;
import org.example.service.EmailSenderService;
import org.example.service.EmailTemplateService;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContextImpl("org.example");
        EmailTemplateService templateService = context.getBean(EmailTemplateService.class);
        templateService.setTemplate("Payment");

        EmailSenderService emailSenderService = context.getBean(EmailSenderService.class);
        emailSenderService.sendEmail();
    }
}