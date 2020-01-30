package com.macramicia.user;

import com.macramicia.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login/authenticate";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user/registration/sign_up";
    }

    @PostMapping("/registration/create")
    public String createUser(@ModelAttribute User user) {
        Role role = new Role();
        role.setName("USER");
        user.setRole(role);

        userService.save(user);
        emailService.sendNewAccountMail(user);
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

    @GetMapping("/update/show")
    public String showUpdateProfilePage() {
        return "user/settings";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, Principal principal) {
        User currentUser = userService.findUserByUsername(principal.getName());

        currentUser.setPassword(user.getPassword());
        currentUser.setEmail(user.getEmail());

        userService.save(currentUser);

        emailService.sendMail(user.getEmail(), "Account Update", "This is to inform you that there has been" +
                " a change of password and/or e-mail for your account.");

        return "redirect:/";
    }
}