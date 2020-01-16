package com.macramicia.courses;

import com.macramicia.user.UserRepository;
import com.macramicia.user.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
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

    private final CourseRepository courseRepository = mock(CourseRepository.class);
	private final UserRepository userRepository = mock(UserRepository.class);
    private final CoursesController coursesController = new CoursesController(courseRepository, userRepository);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(coursesController).build();

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
                .param("time", "12:00")
                .param("venue", "here")
                .param("maxSpots", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("all"));

        ArgumentCaptor<Course> savedCourse = ArgumentCaptor.forClass(Course.class);
        Mockito.verify(courseRepository).save(savedCourse.capture());
        assertThat(savedCourse.getValue().getTitle()).isEqualTo("New Course");
        assertThat(savedCourse.getValue().getDescription()).isEqualTo("You can learn anything you want.");
        assertThat(savedCourse.getValue().getDate()).isEqualTo("2020-01-01");
        assertThat(savedCourse.getValue().getTime()).isEqualTo("12:00");
        assertThat(savedCourse.getValue().getVenue()).isEqualTo("here");
        assertThat(savedCourse.getValue().getMaxSpots()).isEqualTo(100);
    }

    @Test
    public void showAllCourses_rendersAllCourses() throws Exception {

        Course one = new Course();
        one.setTitle("Course one");
        one.setDescription("desc1");
        one.setDate(LocalDate.now());
        one.setTime(LocalTime.now());
        one.setVenue("everywhere");
        one.setMaxSpots(100);

        Course two = new Course();
        one.setTitle("Course two");
        one.setDescription("desc2");
        one.setDate(LocalDate.now());
        one.setTime(LocalTime.now());
        one.setVenue("nowhere");
        one.setMaxSpots(1);

        List<Course> allCourses = asList(one,two);

        when(courseRepository.findAll()).thenReturn(allCourses);

        mockMvc.perform(get("/courses/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("courses"))
                .andExpect(model().attribute("courses", allCourses));
    }
}
