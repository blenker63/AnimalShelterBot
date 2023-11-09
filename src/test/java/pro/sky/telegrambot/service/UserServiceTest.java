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
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
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
    private User user2 = new User(2L, botUser.getName(), LocalDateTime.now(), 11L);
    private List<User> userList = List.of(user, user2);
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
    void findAnimalOwnerByIdTest() {
        when(animalOwnerMock.findAnimalOwnerById(1L)).thenReturn(animalOwner);
        assertThat(service.findAnimalOwnerById(1L)).isEqualTo(animalOwner);
    }

    @Test
    void findPetReportByOwnerIdAndDateTest() {
        when(petReportMock.findPetReportByOwnerIdAndDate(1L, LocalDate.now())).thenReturn(petReport);
        assertThat(service.findPetReportByOwnerIdAndDate(1L, LocalDate.now())).isEqualTo(petReport);

    }

    @Test
    void saveDietReportTest() {
        when(petReportMock.findPetReportByOwnerIdAndDate(1L, LocalDate.now())).thenReturn(petReport);
        service.saveDietReport(1L, "diet");
        verify(petReportMock, times(1)).saveDiet(petReport.getId(), petReport.getDiet());
    }

    @Test
    void saveFeelingsReportTest() {
        when(petReportMock.findPetReportByOwnerIdAndDate(1L, LocalDate.now())).thenReturn(petReport);
        service.saveFeelingsReport(1L, "good");
        verify(petReportMock, times(1)).saveFeelings(petReport.getId(), petReport.getFeelings());
    }

    @Test
    void addPetReportTest() {
        when(petReportMock.save(petReport)).thenReturn(petReport);
        assertThat(service.addPetReport(petReport)).isEqualTo(petReport);
        verify(petReportMock, times(1)).save(petReport);
    }

    @Test
    void addPhotoReportTest() {
        when(photoReportMock.save(photoReport)).thenReturn(photoReport);
        assertThat(service.addPhotoReport(photoReport)).isEqualTo(photoReport);
        verify(photoReportMock, times(1)).save(photoReport);
    }

    @Test
    void findPhotoReportByOwnerIdAndDateTest() {
        when(photoReportMock.findPhotoReportByOwnerIdAndDate(1L, LocalDate.now())).thenReturn(photoReport);
        assertThat(service.findPhotoReportByOwnerIdAndDate(1L, LocalDate.now())).isEqualTo(photoReport);
    }

    @Test
    void checkingLastDateReportsTest() {
        when(petReportMock.checkingLastDateReports(1l)).thenReturn(petReport);
        assertThat(service.checkingLastDateReports(1L)).isEqualTo(petReport);
    }

    @Test
    void allAnimalOwnerTest() {
        List<AnimalOwner> animalOwners = new ArrayList<>();
        animalOwners.add(new AnimalOwner("leo", "88007008090", "mail@mail",
                true, LocalDate.now()));
        animalOwners.add(new AnimalOwner("neo", "88007008050", "mail@gmail",
                true, LocalDate.now()));
        when(animalOwnerMock.findAll()).thenReturn(animalOwners);
        assertThat(service.allAnimalOwner().size()).isEqualTo(2);
    }

    @Test
    void recordDirPhotoTest() {
        when(photoReportMock.recordDirPhoto(1L, "/path")).thenReturn(photoReport);
        assertThat(service.recordDirPhoto(1L,"/path")).isEqualTo(photoReport);
    }

    @Test
    void findAllTest() {
        when(userMock.findAll()).thenReturn(userList);
        assertThat(service.findAll().size()).isEqualTo(2);
        verify(userMock, times(1)).findAll();
    }

    @Test
    void trialPeriodAnimalOwnerTest() {
        var animalOwner =  new AnimalOwner("leo", "88007008090", "mail@mail",
                true, LocalDate.now());
        when(animalOwnerMock.findAnimalOwnerById(1L)).thenReturn(animalOwner);
        LocalDate date = animalOwner.getDate();
        Period period = date.until(LocalDate.now());
        assertThat(service.trialPeriodAnimalOwner(1L)).isEqualTo(
                animalOwner.toString() + " Месяц(ев)-" + period.getMonths() + ", Дней-" + period.getDays());
        verify(animalOwnerMock, times(1)).findAnimalOwnerById(1L);

    }

    @Test
    void trialPeriodOffTest() {
        var animalOwner =  new AnimalOwner("leo", "88007008090", "mail@mail",
                true, LocalDate.now());
        when(animalOwnerMock.findAnimalOwnerById(1L)).thenReturn(animalOwner);
        assertThat(service.trialPeriodOff(1L).getName()).isEqualTo("leo");
        verify(animalOwnerMock, times(1)).trialPeriodOff(1L);

    }
}