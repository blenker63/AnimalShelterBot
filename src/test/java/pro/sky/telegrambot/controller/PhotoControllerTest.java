package pro.sky.telegrambot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Photo;
import pro.sky.telegrambot.repository.*;
import pro.sky.telegrambot.service.AddService;
import pro.sky.telegrambot.service.PhotoService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhotoController.class)
class PhotoControllerTest {
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
    private R_Photo rPhoto;
    @MockBean
    AddService addService;
    @SpyBean
    PhotoService service;

    @Test
    void uploadPhoto() throws Exception {
        var animal = new Animal("cat", "will", 3, "maincoon");
        long animalId = 1L;
        animal.setId(animalId);
        when(rAnimal.getById(animalId)).thenReturn(animal);
        byte[] photos = new byte[]{1, 2, 3};
        MockMultipartFile multipartFile = new MockMultipartFile("name", photos);
        Photo photo = new Photo("/path", 10, "jpg", photos);
        when(rPhoto.save(any(Photo.class))).thenReturn(photo);
        service.uploadPhoto(animalId, multipartFile);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/photo/{animalId}/photo", 1L, multipartFile)
                        .accept(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());
    }
}