package pro.sky.telegrambot.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.model.AnimalOwner;
import pro.sky.telegrambot.repository.*;
import pro.sky.telegrambot.service.AddService;


import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnimalOwnerController.class)
class AnimalOwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private R_AnimalOwner animalOwnerRep;
    @MockBean
    private R_PetReport petReport;
    @MockBean
    private R_Animal rAnimal;
    @MockBean
    private R_Shelter rShelter;
    @MockBean
    private R_Volunteer rVolunteer;

    @SpyBean
    private AddService service;

    @Test
    void animalOwnerSaveTest() throws Exception {
        String name = "leo";
        String phoneNumber = "80080088080";
        String eMail = "mail@mail";
        boolean trial = true;
        Long id = 1L;
        LocalDate date = LocalDate.now();
        JSONObject animalOwnerObject = new JSONObject();
        animalOwnerObject.put("name", name);
        animalOwnerObject.put("phoneNumber", phoneNumber);
        animalOwnerObject.put("eMail", eMail);
        animalOwnerObject.put("trial", trial);
        animalOwnerObject.put("date", date);
        AnimalOwner animalOwner = new AnimalOwner();
        animalOwner.setName("leo");
        animalOwner.setPhoneNumber("80080088080");
        animalOwner.seteMail("mail@mail");
        animalOwner.setTrialPeriod(true);
        animalOwner.setDate(LocalDate.now());
        animalOwner.setId(id);
        System.out.println(animalOwner.toString());
        when(animalOwnerRep.save(any(AnimalOwner.class))).thenReturn(animalOwner);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/animal-owner")
                        .content(animalOwnerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber))
                //.andExpect(jsonPath("$.eMail").value(eMail))
                .andExpect(jsonPath("$.trial").value(trial))
                .andExpect(jsonPath("$.date").value(date))
                .andExpect(jsonPath("$.id").value(id));


    }
}