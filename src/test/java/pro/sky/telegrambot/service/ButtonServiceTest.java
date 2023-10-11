package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import pro.sky.telegrambot.repository.*;

import static java.lang.System.out;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    void setButtonDogShelterInfo() {
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
    void setButtonCatShelterInfo() {
    }

    @Test
    void setButtonLooKAnimal() {
    }

    @Test
    void choiceAnimal() {
    }

    @Test
    void countCat() {
    }

    @Test
    void countDog() {
    }

    @Test
    void findCallBackByAnimal() {
    }

    @Test
    void findPhoto() {
    }
}