package com.groupnine.macramicia;

import com.groupnine.macramicia.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/user")
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }
}
