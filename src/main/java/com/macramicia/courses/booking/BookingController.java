package com.macramicia.courses.booking;

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
@RequestMapping("/courses/profile")
public class BookingController {

	private final UserService userService;

	@Autowired
	public BookingController (UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/addCourse")
	public String updateMyCourses(@ModelAttribute("newCourse") Course newCourse, Principal principal) {
		if (User.getCurrentUser() == null) {
			User.setCurrentUser(userService.userRepository.findUserByUsername(principal.getName()));
		}
		User currentUser = User.getCurrentUser();

		//not working yet
		if (!currentUser.getCourses().contains(newCourse)) {
			currentUser.addCourse(newCourse);
			newCourse.addParticipant(currentUser);
		}
		//add alert in else-block with message: "You already booked this course"

		return "redirect:/courses/profile/show";
	}

	@GetMapping(value = "/show")
	public String showMyCourses(Model model, Principal principal) {
		if (User.getCurrentUser() == null) {
			User.setCurrentUser(userService.userRepository.findUserByUsername(principal.getName()));
		}
		User currentUser = User.getCurrentUser();

		model.addAttribute("myCourses", currentUser.getCourses());
		return "profile";
	}
}
