package com.macramicia.profile;

import com.macramicia.courses.Course;
import com.macramicia.user.User;
import com.macramicia.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	private final UserService userService;

	@Autowired
	public ProfileController (UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/addCourse")
	public String updateMyCourses(@ModelAttribute Course newCourse, Model model, Principal principal) {
		System.out.println(principal.getName());
		System.out.println(userService.userRepository.findAll());

		User currentUser = userService.findUserByUsername(principal.getName());
		currentUser.addCourse(newCourse);

		System.out.println(currentUser.getCourses().get(0).getTitle());

		model.addAttribute("myCourses", currentUser.getCourses());

		System.out.println(model.getAttribute("myCourses"));
		return "profile";
	}

	@GetMapping(value = "/show")
	public String showMyCourses(Model model, Principal principal) {
		User currentUser = userService.userRepository.findUserByUsername(principal.getName());
		if (!model.containsAttribute("myCourses"))
			model.addAttribute("myCourses", currentUser.getCourses());
		return "profile";
	}
}
