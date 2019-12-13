package com.macramicia.courses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.security.test.context.support.WithMockUser;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = CoursesController.class)
class CoursesControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	//@WithMockUser(username="user", password="password",roles={"ADMIN"})
	void whenUserClicksCreateCourse_thenDisplaysNewCoursePage() throws Exception {
		mockMvc.perform(get("/courses/new"))
				.andExpect(view().name("createCourse"))
				.andExpect(status().isOk());
	}


	@Test
	//@WithMockUser(username="user", password="password",roles={"ADMIN"})
	void whenCreateNewCourseAndValidAttributes_thenDisplaysCourses() throws Exception {
		mockMvc.perform(post("/courses/new")
				.content(asJsonString(new Course(new Date(System.currentTimeMillis()))))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(view().name("createCourse"));
	}

	@Test
	void whenUserClicksCourses_thenDisplayAllCourses() throws Exception {
		mockMvc.perform(get("/courses/all"))
				.andExpect(status().isOk())
				.andExpect(view().name("courses"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}