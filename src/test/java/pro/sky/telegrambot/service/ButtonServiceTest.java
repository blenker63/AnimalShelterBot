package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.AnimalOwner;
import pro.sky.telegrambot.model.Photo;
import pro.sky.telegrambot.repository.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.System.out;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ButtonServiceTest {
    @Mock
    private R_Animal rAnimalMock;
    @Mock
    private R_Shelter rShelterMock;
    @Mock
    private R_Photo rPhoto;
    private ButtonService service;

    @BeforeEach
    void initService() {
        service = new ButtonService(rPhoto, rAnimalMock, rShelterMock);
    }


    @Test
    void setButtonStartMenuTest() {
        Long chatId = 152152L;
        String text = "test";
        SendMessage test = service.setButtonStartMenu(chatId, text);
        assertThat(test).isNotNull().isInstanceOf(SendMessage.class);

    }

    @Test
    void setButtonDogShelterInfoTest() {
        Long chatId = 152152L;
        String text = "test";
        SendMessage test = service.setButtonDogShelterInfo(chatId, text);
        SendMessage expected = new SendMessage();
        expected.setChatId(152152L);
        expected.setText("test");
        assertThat(test).isNotNull().isInstanceOf(SendMessage.class);
        assertThat(test.getChatId()).isEqualTo(expected.getChatId());
        assertThat(test.getText()).isEqualTo(expected.getText());
    }

    @Test
    void setButtonCatShelterInfoTest() {
        Long chatId = 152152L;
        String text = "test";
        SendMessage test = service.setButtonCatShelterInfo(chatId, text);
        SendMessage expected = new SendMessage();
        expected.setChatId(152152L);
        expected.setText("test");
        assertThat(test).isNotNull().isInstanceOf(SendMessage.class);
        assertThat(test.getChatId()).isEqualTo(expected.getChatId());
        assertThat(test.getText()).isEqualTo(expected.getText());
    }

    @Test
    void setButtonLooKAnimalTest() {
        Long chatId = 152152L;
        String text = "test";
        String animalId = "testanimal";
        SendMessage test = service.setButtonLooKAnimal(chatId, animalId, text);
        assertThat(test).isNotNull().isInstanceOf(SendMessage.class);
        SendMessage expected = new SendMessage();
        expected.setChatId(chatId);
        expected.setText(text);
        assertThat(test.getChatId()).isEqualTo(expected.getChatId());
        assertThat(test.getText()).isEqualTo("\t" + expected.getText());
    }

    @Test
    void choiceAnimalTest() {
        Long chatId = 152152L;
        String text = "test";
        String animalId = "testanimal";
        SendMessage test = service.choiceAnimal(chatId, animalId, text);
        assertThat(test).isNotNull().isInstanceOf(SendMessage.class);
        SendMessage expected = new SendMessage();
        expected.setChatId(chatId);
        expected.setText(text);
        assertThat(test.getChatId()).isEqualTo(expected.getChatId());
        assertThat(test.getText()).isEqualTo("    " + expected.getText());
    }

    @Test
    void countCatTest() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("кот", "will", 3, "maincoon"));
        animals.add(new Animal("кот", "will1", 3, "maincoon"));
        animals.add(new Animal("собака", "bill", 2, "colli"));
        String type = "кот";
        when(rAnimalMock.findAnimalByAnimalTypeIgnoreCase(type)).thenReturn(animals.subList(0, 2));
        assertThat(service.countCat().size()).isEqualTo(2);
        verify(rAnimalMock, times(1)).findAnimalByAnimalTypeIgnoreCase(type);
    }

    @Test
    void countDogTest() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("кот", "will", 3, "maincoon"));
        animals.add(new Animal("кот", "will1", 3, "maincoon"));
        animals.add(new Animal("собака", "bill", 2, "colli"));
        String type = "собака";
        when(rAnimalMock.findAnimalByAnimalTypeIgnoreCase(type)).thenReturn(animals.subList(2, 3));
        assertThat(service.countDog().size()).isEqualTo(1);
        verify(rAnimalMock, times(1)).findAnimalByAnimalTypeIgnoreCase(type);
    }

    @Test
    void findCallBackByAnimalTest() {
        Animal animal = new Animal("кот", "will", 3, "maincoon");
        when(rAnimalMock.findAnimalById(1L)).thenReturn(animal);
        assertThat(service.findCallBackByAnimal(1L).getName()).isEqualTo("will");
        verify(rAnimalMock, times(1)).findAnimalById(1L);

    }

    @Test
    void findPhotoTest() {

        byte[] data = new byte[]{1, 2, 3};
        Photo photo = new Photo("filepath", 1024L, "bng", data);
        when(rPhoto.findPhotoByAnimalId(1L)).thenReturn(photo);
        assertThat(service.findPhoto(1L)).isEqualTo(photo);
        verify(rPhoto, times(1)).findPhotoByAnimalId(1L);
    }

    @Test
    void informationForAdoptingAnAnimal() {
    }

    @Test
    void sendReport() {
    }

    @Test
    void findAnimalTypeById() {
    }

    @Test
    void findPhoto() {
    }

    @Test
    void setCatMenu() {
    }
}