package com.macramicia.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CoursesController {

	private CourseRepository courseRepository;

	public CoursesController(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@GetMapping(value = "/new")
	public String addCourse(@Valid Course course, Model model) {
		model.addAttribute("course", course);
		return "createCourse";
	}

	@PostMapping(value = "/show")
	public String addCourse(@Valid Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "createCourse";
		}
		courses.save(course);
		model.addAttribute("courses", courses.findAll());
		return "courses";
	}

	@GetMapping(value = "/all")
	public String showAllCourses(Model model) {
		model.addAttribute("courses", courses.findAll());
		return "courses";
	}
}