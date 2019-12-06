package com.groupnine.macramicia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/user")
public class RegistrationController {

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        return "registration";
    }
}
