package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Photo;
import pro.sky.telegrambot.repository.R_Animal;
import pro.sky.telegrambot.repository.R_Photo;
import pro.sky.telegrambot.repository.R_Shelter;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        long chatId = 152152L;
        String text = "test";
        SendMessage test = service.setButtonStartMenu(chatId, text);
        assertThat(test).isNotNull().isInstanceOf(SendMessage.class);

    }

    @Test
    void setButtonDogShelterInfoTest() {
        long chatId = 152152L;
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
        long chatId = 152152L;
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
        long chatId = 152152L;
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
        long chatId = 152152L;
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
    void informationForAdoptingAnAnimalTest() {
        long chatId = 1L;
        String animalType = "cat";
        String text = "good";
        SendMessage test = service.informationForAdoptingAnAnimal(chatId, animalType, text);
        assertThat(test).isNotNull().isInstanceOf(SendMessage.class);
        SendMessage expected = new SendMessage();
        expected.setChatId(chatId);
        expected.setText(text);
        assertThat(test.getChatId()).isEqualTo(expected.getChatId());
        assertThat(test.getText()).isEqualTo(expected.getText());
    }

    @Test
    void sendReportTest() {
        long chatId = 1L;
        String text = "good";
        SendMessage test = service.sendReport(chatId, text);
        assertThat(test).isNotNull().isInstanceOf(SendMessage.class);
        SendMessage expected = new SendMessage();
        expected.setChatId(chatId);
        expected.setText(text);
        assertThat(test.getChatId()).isEqualTo(expected.getChatId());
        assertThat(test.getText()).isEqualTo(expected.getText());
    }

    @Test
    void findAnimalTypeByIdTest() {
        Animal animal = new Animal("кот", "will", 3, "maincoon");
        when(rAnimalMock.findAnimalTypeById(1L)).thenReturn(animal.getAnimalType());
        assertThat(service.findAnimalTypeById(1L)).isEqualTo("кот");
        verify(rAnimalMock, times(1)).findAnimalTypeById(1L);
    }


    @Test
    void setCatMenu() {
        long chatId = 1L;
        String text = "good";
        SendMessage test = service.setCatMenu(chatId, text);
        assertThat(test).isNotNull().isInstanceOf(SendMessage.class);
        SendMessage expected = new SendMessage();
        expected.setChatId(chatId);
        expected.setText(text);
        assertThat(test.getChatId()).isEqualTo(expected.getChatId());
        assertThat(test.getText()).isEqualTo(expected.getText());
    }
}