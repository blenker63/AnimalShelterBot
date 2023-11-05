package pro.sky.telegrambot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.repository.*;
import pro.sky.telegrambot.service.AddService;
import pro.sky.telegrambot.service.PetReportService;
import pro.sky.telegrambot.service.UserService;

import javax.validation.executable.ValidateOnExecution;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetReportController.class)
class PetReportControllerTest {
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
    @MockBean
    UserService userService;
    @SpyBean
    PetReportService service;



    @Test
    void readAllReportTest() throws Exception {
        var report1 = new PetReport(1L, "meat", "good",  true, LocalDate.now());
        var report2 = new PetReport(2L, "chick", "verygood",  true, LocalDate.now());
        List<PetReport> list = List.of(report1, report2);
        when(petReport.findAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/pet-report/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()))
                .andDo(print());
    }

    @Test
    void readPetReportDateTest() throws Exception {
        var report1 = new PetReport(1L, "meat", "good",  true, LocalDate.now());
        var report2 = new PetReport(2L, "chick", "verygood",  true, LocalDate.now());
        List<PetReport> list = List.of(report1, report2);
        when(petReport.findByDate(LocalDate.now())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet-report/date?date=2023-11-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()))
                .andDo(print());
    }

    @Test
    void readById() throws Exception {
        var report1 = new PetReport(1L, "meat", "good",  true, LocalDate.now());
        var report2 = new PetReport(1L, "chick", "verygood",  true, LocalDate.now());
        List<PetReport> list = List.of(report1, report2);
        when(petReport.findById(1L)).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet-report/id?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()))
                .andDo(print());
    }

    @Test
    void readControl() throws Exception {
        var report1 = new PetReport(1L, "meat", "good",  true, LocalDate.now());
        var report2 = new PetReport(2L, "chick", "verygood",  true, LocalDate.now());
        List<PetReport> list = List.of(report1, report2);
        when(petReport.findByControl(true)).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet-report/check?control=true")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()))
                .andDo(print());
    }
}