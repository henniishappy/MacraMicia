package com.groupnine.macramicia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(Principal user) {
        if(user != null && ((Authentication)user).isAuthenticated()) {
            return "index";
        }
        else return "login";
    }

}
