package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.model.Animal;
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

    @BeforeEach
    void setUp() {

        service = new PhotoService(rPhoto, rAnimal);
        ReflectionTestUtils.setField(service, "photosDir", "photos");
    }

    @Test
    void uploadPhoto() throws IOException {
        var animal = new Animal("cat", "will", 3, "maincoon");
        Long animalId = 1L;
        animal.setId(animalId);
        byte[] photos = new byte[]{1, 2, 3};
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getSize()).thenReturn(100L);
        when(multipartFile.getContentType()).thenReturn("content");
        when(multipartFile.getBytes()).thenReturn(photos);
        Path filePath = mock(Path.class);
        when(filePath.toString()).thenReturn("/photos");
        String direct = "direct";
        String filename = "name";
        when(multipartFile.getOriginalFilename()).thenReturn(filename);
        //filePath = Path.of(direct, animal + "." +
         //       Objects.requireNonNull(filename));
        var photo = new Photo();
        photo.setAnimal(animal);
        photo.setFilePath(filePath.toString());
        photo.setFileSize(multipartFile.getSize());
        photo.setMediaType(multipartFile.getContentType());
        photo.setData(multipartFile.getBytes());
        when(rAnimal.getById(animalId)).thenReturn(animal);
        when(rPhoto.save(photo)).thenReturn(photo);
        service.uploadPhoto(animalId, multipartFile);
        verify(rPhoto, times(1)).save(photo);


    }


    @Test
    void findPhoto() {
    }

    @Test
    void avatarPage() {
    }
}