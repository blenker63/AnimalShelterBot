package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.AnimalOwner;
import pro.sky.telegrambot.model.Photo;
import pro.sky.telegrambot.repository.R_Animal;
import pro.sky.telegrambot.repository.R_Photo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhotoServiceTest {
    @Mock
    private R_Photo rPhoto;
    @Mock
    private R_Animal rAnimal;
    PhotoService service;
    @Captor
    ArgumentCaptor<Photo> captor;

    @BeforeEach
    void setUp() {

        service = new PhotoService(rPhoto, rAnimal);
        ReflectionTestUtils.setField(service, "photosDir", "photos");
    }

    @Test
    void uploadPhoto() throws IOException {
        Animal animal = new Animal("cat", "will", 3, "maincoon");
        long animalId = 1L;
        animal.setId(animalId);
        byte[] photos = new byte[]{1, 2, 3};
        MockMultipartFile multipartFile = new MockMultipartFile("name", photos);

        verify(rPhoto, times(1)).save(captor.capture());
    }


}