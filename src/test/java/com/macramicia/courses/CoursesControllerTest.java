package com.macramicia.courses;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import org.mockito.ArgumentCaptor;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CoursesControllerTest {

    private CourseRepository courseRepository = mock(CourseRepository.class);
    private CoursesController coursesController = new CoursesController(courseRepository);
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(coursesController).build();

/*
    @BeforeEach
    void setUp() {
        courseRepository = mock(CourseRepository.class);
        CoursesController coursesController = new CoursesController(courseRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(coursesController).build();
    }*/

    @Test
    public void newCourse_rendersCourseForm() throws Exception {
        mockMvc.perform(get("/courses/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("createCourse"))
                .andExpect(model().attributeExists("course"));
    }

    @Test
    public void createCourse_savesCourseInRepository() throws Exception {
        mockMvc.perform(post("/courses/show")
                .param("title", "New Course")
                .param("description", "You can learn anything you want.")
                .param("date","2020-01-01")
                .param("venue", "here")
                .param("maxSpots", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("courses"));

        ArgumentCaptor<Course> savedCourse = ArgumentCaptor.forClass(Course.class);
        Mockito.verify(courseRepository).save(savedCourse.capture());
        assertThat(savedCourse.getValue().getTitle()).isEqualTo("New Course");
        assertThat(savedCourse.getValue().getDescription()).isEqualTo("You can learn anything you want.");
        assertThat(savedCourse.getValue().getDate()).isEqualTo("2020-01-01");
        assertThat(savedCourse.getValue().getVenue()).isEqualTo("here");
        assertThat(savedCourse.getValue().getMaxSpots()).isEqualTo(100);
    }

    @Test
    public void showAllCourses_rendersAllCourses() throws Exception {
        List<Course> allCourses = asList(
                new Course("course one", "a description", LocalDateTime.now(), "everywhere", 100),
                new Course ("course two", "also a description", LocalDateTime.now(), "nowhere", 1));


        when(courseRepository.findAll()).thenReturn(allCourses);

        mockMvc.perform(get("/courses/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses"))
                .andExpect(model().attribute("courses", allCourses));
    }
}
