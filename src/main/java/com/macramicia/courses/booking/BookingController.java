package com.macramicia.courses.booking;

import com.macramicia.courses.Course;
import com.macramicia.user.User;
import com.macramicia.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/courses/profile")
public class BookingController {

	private final UserService userService;

	@Autowired
	public BookingController (UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/book")
	public String updateMyCourses(@ModelAttribute("newCourse") Course newCourse, Principal principal) {
		User currentUser = userService.findUserByUsername(principal.getName());

		if (!currentUser.getCourses().contains(newCourse)) {
			currentUser.addCourse(newCourse);
			newCourse.addParticipant(currentUser);

			userService.save(currentUser);
		}

		return "redirect:/courses/profile/show";

	}

	@GetMapping(value = "/show")
	public String showMyCourses(Model model, Principal principal) {
		User currentUser = userService.findUserByUsername(principal.getName());

		model.addAttribute("myCourses", currentUser.getCourses());
		return "profile";
	}
}