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
import java.util.Set;

@Controller
@RequestMapping("/courses/booking")
public class BookingController {

	private final UserService userService;

	@Autowired
	public BookingController (UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/new")
	public String updateMyCourses(@ModelAttribute("newCourse") Course newCourse, Principal principal) {
		User currentUser = userService.findUserByUsername(principal.getName());

		Set<Course> courses = currentUser.getCourses();
		for (Course course : courses) {
			if (course.getId() == newCourse.getId())
				return "redirect:/";
		}

		if (newCourse.addParticipant(currentUser)) {
			currentUser.addCourse(newCourse);
			userService.save(currentUser);
		}
		return "redirect:/user/profile/show";

		/*
		if (!currentUser.getCourses().contains(newCourse)) {

			//newCourse.addParticipant(currentUser);
			userService.save(currentUser);


			if (newCourse.addParticipant(currentUser)) {
				currentUser.addCourse(newCourse);

				userService.save(currentUser);
			}
			return "redirect:/user/profile/show";
		}
		return "redirect:/";
		*/
	}
}