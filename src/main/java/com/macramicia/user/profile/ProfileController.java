package com.macramicia.user.profile;

import com.macramicia.user.User;
import com.macramicia.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user/profile")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/show")
    public String showMyCourses(Model model, Principal principal) {
        User currentUser = userService.findUserByUsername(principal.getName());

        model.addAttribute("myCourses", currentUser.getCourses());
        return "user/profile/profile";
    }
}
