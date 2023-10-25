package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.*;
import pro.sky.telegrambot.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private R_BotUser botUserMock;
    @Mock
    private R_User userMock;
    @Mock
    private R_AnimalOwner animalOwnerMock;
    @Mock
    private R_PetReport petReportMock;
    @Mock
    private R_PhotoReport photoReportMock;
    UserService service;


    @BeforeEach
    void setUp() {
        service = new UserService(botUserMock, userMock, animalOwnerMock, petReportMock, photoReportMock);
    }

    private BotUser botUser = new BotUser(1L, "leo", LocalDateTime.now());
    private User user = new User(1L, botUser.getName(), LocalDateTime.now(), 10L);
    private AnimalOwner animalOwner = new AnimalOwner("leo", "88007008090", "mail@mail",
            true, LocalDate.now());
    private PetReport petReport = new PetReport(1L, "diet", "good", true, LocalDate.now());
    private PhotoReport photoReport = new PhotoReport(1L, LocalDate.now(), "/path");

    @Test
    void saveBotUserTest() {
        when(botUserMock.save(botUser)).thenReturn(botUser);
        assertThat(service.saveBotUser(1L, "leo")).isEqualTo(botUser);
    }

    @Test
    void saveUserTest() {
        when(botUserMock.findBotUserByUserId(1L)).thenReturn(botUser);
        when(userMock.save(user)).thenReturn(user);
        assertThat(service.saveUser(1L, 10L)).isEqualTo(user);
    }

    @Test
    void savePhoneUserTest() {
        String phone = "+79201234565";
        Long chatId = 10L;
        service.savePhoneUser(chatId, phone);
        verify(userMock, times(1)).savePhone(chatId, phone);
    }

    @Test
    void findUserByIdTest() {
        when(botUserMock.findBotUserByUserId(1L)).thenReturn(botUser);
        assertThat(service.findUserById(1L)).isEqualTo(botUser);
    }

    @Test
    void findUserAndAnimalTest() {
        when(userMock.findUserByChatId(1L)).thenReturn(user);
        assertThat(service.findUserAndAnimal(1L)).isEqualTo(user);
    }

    @Test
    void findAnimalOwnerById() {
        when(animalOwnerMock.findAnimalOwnerById(1L)).thenReturn(animalOwner);
        assertThat(service.findAnimalOwnerById(1L)).isEqualTo(animalOwner);
    }

    @Test
    void findPetReportByOwnerIdAndDate() {
        when(petReportMock.findPetReportByOwnerIdAndDate(1L, LocalDate.now())).thenReturn(petReport);
        assertThat(service.findPetReportByOwnerIdAndDate(1L, LocalDate.now())).isEqualTo(petReport);

    }

    @Test
    void saveDietReport() {
    }

    @Test
    void saveFeelingsReport() {
    }

    @Test
    void addPetReport() {
    }

    @Test
    void addPhotoReport() {
    }

    @Test
    void findPhotoReportByOwnerIdAndDate() {
    }

    @Test
    void checkingLastDateReports() {
    }
}