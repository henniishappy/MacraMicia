package com.macramicia.user;

import com.macramicia.BCryptEncoderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private BCryptEncoderConfig encoderConfig;

    @Autowired
    public UserController(UserService userService, BCryptEncoderConfig encoderConfig) {
        this.userService = userService;
        this.encoderConfig = encoderConfig;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration/create")
    public String createUser(@ModelAttribute User user) {
        String encryptedPw = encoderConfig.passwordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPw);
        userService.saveUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String showLogoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/user/login?logout";
    }
}
