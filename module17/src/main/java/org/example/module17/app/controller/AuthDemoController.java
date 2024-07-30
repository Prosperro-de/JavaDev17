package org.example.module17.app.controller;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthDemoController {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @GetMapping
    public String hello (Principal principal) {
//        System.out.println(principal.getClass());
//        threadLocal.set("I'm in thread local");
//        return principal.getName();
//    }
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication.getName();
    }
}
