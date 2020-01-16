package com.macramicia.profile;

import com.macramicia.courses.Course;
import com.macramicia.user.User;
import com.macramicia.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String updateMyCourses(@ModelAttribute Course course, Model model, Principal principal) {
		User currentUser = userService.userRepository.findUserByUsername(principal.getName());
		currentUser.addCourse(course);
		model.addAttribute("myCourses", currentUser.getCourses());
		return "profile";
	}

	@GetMapping(value = "/show")
	public String showMyCourses(Model model, Principal principal) {
		User currentUser = userService.userRepository.findUserByUsername(principal.getName());
		System.out.println(currentUser);
		if (!model.containsAttribute("myCourses"))
			model.addAttribute("myCourses", currentUser.getCourses());
		return "profile";
	}
}
