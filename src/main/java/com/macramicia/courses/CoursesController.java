package com.macramicia.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CoursesController {

	private final CourseRepository courseRepository;

	@Autowired
	public CoursesController(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@GetMapping(value = "/new")
	public String newCourse(Model model) {
		model.addAttribute("course", new Course());
		return "createCourse";
	}

	@PostMapping(value = "/create")
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

	@PostMapping(value = "/addCourseToProfile")
	public String addCourseToProfile(@ModelAttribute("newCourse") Course newCourse, Model model) {
		model.addAttribute("newCourse", new Course());
		return "redirect:/profile/addCourse";
	}
}