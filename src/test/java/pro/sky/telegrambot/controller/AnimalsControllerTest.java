package pro.sky.telegrambot.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.repository.*;
import pro.sky.telegrambot.service.AddService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnimalsController.class)
class AnimalsControllerTest {
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
    void animalSaveTest() throws Exception {
        String animalType = "cat";
        String name = "Tom";
        int age = 3;
        String breed = "siam";
        long id = 1L;
        JSONObject animalObject = new JSONObject();
        animalObject.put("cat", animalType);
        animalObject.put("Tom", name);
        animalObject.put(String.valueOf(3), age);
        animalObject.put("siam", breed);
        Animal animal = new Animal();
        animal.setAnimalType("cat");
        animal.setName("Tom");
        animal.setAge(3);
        animal.setBreed("siam");
        animal.setId(id);
        when(rAnimal.save(any(Animal.class))).thenReturn(animal);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/animal")
                        .content(animalObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.animalType").value(animalType))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.breed").value(breed))
                .andExpect(jsonPath("$.id").value(id))
                .andDo(print());


    }
}