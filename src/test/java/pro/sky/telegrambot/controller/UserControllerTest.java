package pro.sky.telegrambot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.*;
import pro.sky.telegrambot.service.AddService;
import pro.sky.telegrambot.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private R_Animal rAnimal;
    @MockBean
    private R_PetReport petReport;
    @MockBean
    private R_AnimalOwner rAnimalOwner;
    @MockBean
    private R_Shelter rShelter;
    @MockBean
    private R_Volunteer rVolunteer;
    @MockBean
    AddService addService;
    @MockBean
    R_BotUser rBotUser;
    @MockBean
    R_User rUser;
    @MockBean
    R_PhotoReport rPhotoReport;
    @SpyBean
    UserService service;

    @Test
    void getAllUserTest() throws Exception {
        User user1 = new User(1L, "leo", LocalDateTime.now(), 1L);
        user1.setTelephone("808080");
        User user2 = new User(2L, "alex", LocalDateTime.now(), 2L);
        user2.setTelephone("909090");
        List<User> userList = List.of(user1, user2);
        when(rUser.findAll()).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(userList.size()))
                .andDo(print());


    }
}