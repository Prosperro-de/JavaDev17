package org.spring.demo.module15.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {

    @GetMapping("/createCustomerForm")
    public String createCustomerForm(Model model) {
        model.addAttribute("action", "create");
        return "index";
    }

    @GetMapping("/findCustomerByIdForm")
    public ModelAndView findCustomerByIdForm() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("action", "findById");
        return model;
    }

    @GetMapping("/findCustomerByEmailForm")
    public ModelAndView findCustomerByEmailForm() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("action", "findByEmail");
        return model;
    }

    @GetMapping("/deleteCustomerByIdForm")
    public ModelAndView deleteCustomerByIdForm() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("action", "delete");
        return model;
    }

}
