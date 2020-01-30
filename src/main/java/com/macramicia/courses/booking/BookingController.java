package com.macramicia.courses.booking;

import com.macramicia.courses.Course;
import com.macramicia.courses.CourseRepository;
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
	private final CourseRepository courseRepository;

	@Autowired
	public BookingController (UserService userService, CourseRepository courseRepository) {
		this.userService = userService;
		this.courseRepository = courseRepository;
	}

	@PostMapping(value = "/book")
	public String updateMyCourses(@ModelAttribute("newCourse") Course newCourse, Principal principal) {
		User currentUser = userService.findUserByUsername(principal.getName());

		if (!currentUser.getCourses().contains(newCourse)) {
			if (newCourse.addParticipant(currentUser)) {
				currentUser.addCourse(newCourse);

				userService.save(currentUser);
			}

			return "redirect:/courses/profile/show";

		}

		return "redirect:/";

	}

	@GetMapping(value = "/show")
	public String showMyCourses(Model model, Principal principal) {
		User currentUser = userService.findUserByUsername(principal.getName());

		model.addAttribute("myCourses", currentUser.getCourses());
		return "profile";
	}
}