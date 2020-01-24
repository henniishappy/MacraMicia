package com.macramicia.courses;

import com.macramicia.EmailService;
import com.macramicia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.SendFailedException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/courses")
public class CoursesController {

	private final CourseRepository courseRepository;
	private final EmailService emailService;

	@Autowired
	public CoursesController(CourseRepository courseRepository, EmailService emailService) {
		this.courseRepository = courseRepository;
		this.emailService = emailService;
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

	/*
	@PostMapping(value = "/addCourseToProfile")
	public String addCourseToProfile(@ModelAttribute("newCourse") Course newCourse, Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("newCourse", newCourse);
		model.addAttribute("newCourse", newCourse);
		return "redirect:/courses/profile/book";
	}*/

	@DeleteMapping(value = "/remove")
	public String deleteCourse(@ModelAttribute("course") Course course) throws SendFailedException {
		Set<User> participants = course.getParticipants();
		String name = course.getTitle();

		courseRepository.delete(course);

		for(User user : participants) {
			emailService.sendMail(user.getEmail(), "Your booked course " + name, "We are sorry to inform you" +
					" that your course " + name + " got cancelled.");
		}

		return "redirect:/courses";
	}

	@PutMapping(value = "/update")
	public String updateCourse(@ModelAttribute("course") Course course) throws SendFailedException {
		Course courseToUpdate = courseRepository.findCourseById(course.getId());
		String name = course.getTitle();
		Set<User> participants = course.getParticipants();

		courseToUpdate.setVenue(course.getVenue());
		courseToUpdate.setDate(course.getDate());
		courseToUpdate.setTime(course.getTime());

		courseRepository.save(courseToUpdate);

		for(User user : participants) {
			emailService.sendMail(user.getEmail(), "Your booked course " + name, "We would like to inform you" +
					" that your course " + name + " has changed its date and/or venue and/or time. Please check under your " +
					"profile for further information");
		}

		return "redirect:/courses";
	}

}