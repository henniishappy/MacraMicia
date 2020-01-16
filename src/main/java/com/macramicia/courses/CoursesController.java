package com.macramicia.courses;

import com.macramicia.user.User;
import com.macramicia.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CoursesController {

	private final CourseRepository courseRepository;
	private final UserRepository userRepository;

	@Autowired
	public CoursesController(CourseRepository courseRepository, UserRepository userRepository) {
		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
	}

	@GetMapping(value = "/new")
	public String newCourse(Model model) {
		model.addAttribute("course", new Course());
		return "createCourse";
	}

	@PostMapping(value = "/show")
	public String createCourse(@ModelAttribute Course course) {
		courseRepository.save(course);
		return "redirect:all";
	}

	@GetMapping(value = "/all")
	public String showAllCourses(Model model) {
		List<Course> allCourses = courseRepository.findAll();
		model.addAttribute("courses", allCourses);
		return "courses";
	}

	@GetMapping(value = "/profile/addCourse")
	public String updateMyCourses(@ModelAttribute Course course, Model model, Principal principal) {
		User currentUser = userRepository.findUserByUsername(principal.getName());
		currentUser.addCourse(course);
		model.addAttribute("myCourses", currentUser.getCourses());
		return "profile";
	}

	@GetMapping(value = "/profile")
	public String showMyCourses(Model model, Principal principal) {
		//System.out.println(userRepository.findAll());
		//System.out.println(principal.getName());
		User currentUser = userRepository.findUserByUsername(principal.getName());
		System.out.println(currentUser);
		if (!model.containsAttribute("myCourses"))
			model.addAttribute("myCourses", currentUser.getCourses());
		return "profile";
	}
}