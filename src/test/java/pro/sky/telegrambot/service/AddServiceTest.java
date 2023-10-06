package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.repository.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddServiceTest {
    @Mock
    private R_Animal rAnimalMock;
    @Mock
    private R_AnimalOwner rAnimalOwnerMock;
    @Mock
    private R_Information rInformationMock;
    @Mock
    private R_PetReport rPetReportMock;
    @Mock
    private R_Shelter rShelterMock;
    @Mock
    private R_User rUserMock;
    @Mock
    private R_Volunteer rVolunteerMock;

    private AddService service;

    @BeforeEach
    void initService() {
        service = new AddService(rAnimalMock, rAnimalOwnerMock, rInformationMock, rPetReportMock,
                rShelterMock, rUserMock, rVolunteerMock);
    }

    @Test
    void animalSave() {
        var animal = new Animal("cat", "will", 3, "maincoon");
        when(rAnimalMock.save(animal)).thenReturn(animal);
        assertEquals(animal, service.AnimalSave("cat", "will", 3, "maincoon"));
        //verify(rAnimalMock, times(1));
    }

    @Test
    void animalOwnerSave() {
    }

    @Test
    void informationSave() {
    }

    @Test
    void petReportSave() {
    }

    @Test
    void shelterSave() {
    }

    @Test
    void userSave() {
    }

    @Test
    void volunteerSave() {
    }
}