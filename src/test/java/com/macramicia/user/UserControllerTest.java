package com.macramicia.user;

import com.macramicia.BCryptEncoderConfig;
import com.macramicia.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private final UserService userService = mock(UserService.class);
    private final EmailService emailService = mock(EmailService.class);
    private final BCryptEncoderConfig encoderConfig = mock(BCryptEncoderConfig.class);
    private final UserController userController = new UserController(userService, emailService);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    @Test
    public void getLoginPageRendersLogin() throws Exception {
        mockMvc.perform(get(
                "/user/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("authenticate"));
    }

    @Test
    public void newUserRendersSignUpForm() throws Exception {
        mockMvc.perform(get("/user/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign_up"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void createNewUserSavesUserToRepository() throws Exception {
        mockMvc.perform(post("/user/registration/create")
            .param("username", "user")
            .param("password", "password")
            .param("firstName", "Us")
            .param("lastName", "Pas")
            .param("email", "abc@gmx.de"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/login"));

        ArgumentCaptor<User> savedUser = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userService).saveUser(savedUser.capture());
        assertThat(savedUser.getValue().getFirstName()).isEqualTo("Us");
        assertThat(savedUser.getValue().getLastName()).isEqualTo("Pas");
        assertThat(savedUser.getValue().getUsername()).isEqualTo("user");
    }

}