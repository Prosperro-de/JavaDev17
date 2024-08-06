package org.example.module18.app.controller;

import lombok.RequiredArgsConstructor;
import org.example.module18.app.model.dto.request.AuthRequest;
import org.example.module18.app.service.CustomerService;
import org.example.module18.app.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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

    @PostMapping("/signup")
    public String signup(@RequestBody AuthRequest authRequest) {
        return userService.createUser(authRequest);
    }
}
