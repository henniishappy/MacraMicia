package com.groupnine.macramicia;

import com.groupnine.macramicia.courses.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/courses")
public class CoursesController {
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCourse() {
		return "createCourse";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCourse(@ModelAttribute Course course, Model model) {
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
