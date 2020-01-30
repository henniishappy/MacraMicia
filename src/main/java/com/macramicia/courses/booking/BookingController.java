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
	private final CourseRepository courseRepository;

	@Autowired
	public BookingController (UserService userService, CourseRepository courseRepository) {
		this.userService = userService;
		this.courseRepository = courseRepository;
	}

	@PostMapping(value = "/new")
	public String updateMyCourses(@ModelAttribute("newCourse") Course newCourse, Principal principal) {

		User currentUser = userService.findUserByUsername(principal.getName());
		newCourse = courseRepository.findCourseByTitle(newCourse.getTitle());

		Set<Course> courses = currentUser.getCourses();
/*
		for (Course course : courses) {
			if (course.getId() == newCourse.getId())
				return "redirect:/courses/all?error";
		}*/

		if (newCourse.addParticipant(currentUser)) {
			courses.add(newCourse);
			currentUser.setCourses(courses);
			courseRepository.save(newCourse);
			userService.save(currentUser);
			return "redirect:/user/profile/show";
		}
		else return "redirect:/courses/all?error";

	}
}