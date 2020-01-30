package com.macramicia.courses;

import com.macramicia.EmailService;
import com.macramicia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
		return "courses/createCourse";
	}

	@PostMapping(value = "/create")
	public String createCourse(@ModelAttribute Course course) {
		if(courseRepository.findCourseByTitle(course.getTitle()) != null) {
			return "redirect:/courses/new?error";
		} else {
			course.setFreeSpots(course.getMaxSpots());
			courseRepository.save(course);
		}
		return "redirect:all";
	}

	@GetMapping(value = "/all")
	public String showAllCourses(Model model) {
		List<Course> allCourses = courseRepository.findAll();
		model.addAttribute("courses", allCourses);
		return "courses/courses";
	}

	@DeleteMapping(value = "/remove")
	public String deleteCourse(@ModelAttribute("course") Course course) {
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
	public String updateCourse(@ModelAttribute("course") Course course) {
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