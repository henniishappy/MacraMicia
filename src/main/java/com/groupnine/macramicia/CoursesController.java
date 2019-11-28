package com.groupnine.macramicia;

import com.groupnine.macramicia.courses.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/courses")
public class CoursesController {
	@GetMapping(value = "/add")
	public String addCourse(@Valid Course course, Model model) {
		model.addAttribute("course", course);
		return "createCourse";
	}

	@PostMapping(value = "/add")
	public String addCourse(@Valid Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "createCourse";
		}
		Course.addCourse(course);
		model.addAttribute("courses", Course.getCourses());
		return "courses";
	}

	@GetMapping(value = "/all")
	public String showAllCourses(Model model) {
		model.addAttribute("courses", Course.getCourses());
		return "courses";
	}
}
