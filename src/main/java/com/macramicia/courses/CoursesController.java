package com.macramicia.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
//@RequestMapping("/courses")
public class CoursesController {

	private CourseRepository courseRepository;

	public CoursesController(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@GetMapping(value = "/new")
	public String newCourse(Model model) {
		model.addAttribute("course", new Course());
		return "createCourse";
	}

	@PostMapping(value = "/show")
	public String createCourse(@ModelAttribute Course course) throws ExecutionException, InterruptedException {
		courseRepository.save(course);
		return "redirect:courses";
	}

	@GetMapping(value = "/all")
	public String showAllCourses(Model model) {
		List<Course> allCourses = courseRepository.findAll();
		model.addAttribute("courses", allCourses);
		return "courses";
	}
}