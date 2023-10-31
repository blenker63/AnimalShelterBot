package pro.sky.telegrambot.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.model.Shelter;
import pro.sky.telegrambot.repository.*;
import pro.sky.telegrambot.service.AddService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(InfoController.class)
class InfoControllerTest {
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
    @SpyBean
    AddService service;


    @Test
    void shelterSave() throws Exception {
        String type = "catShelter";
        String name = "shelter";
        String address = "Astana";
        String info = "info";
        long id = 1L;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("catShelter", type);
        jsonObject.put("shelter", name);
        jsonObject.put("Astana", address);
        jsonObject.put("info", info);
        Shelter shelter = new Shelter();
        shelter.setShelterType(type);
        shelter.setShelterName(name);
        shelter.setAddress(address);
        shelter.setInformation(info);
        when(rShelter.save(new Shelter("catShelter", "shelter", "Astana", "info")))
                .thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/shelter")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shelterType").value(type))
                .andExpect(jsonPath("$.shelterName").value(name))
                .andExpect(jsonPath("$.address").value(address))
                .andExpect(jsonPath("$.information").value(info))
                .andDo(print());
    }
}