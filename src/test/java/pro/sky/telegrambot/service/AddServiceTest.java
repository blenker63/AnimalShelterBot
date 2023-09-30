package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.repository.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddServiceTest {
    @Mock
    private R_Animal rAnimalMock;
    @Mock
    private R_AnimalOwner rAnimalOwnerMock;
    private R_Information rInformationMock;
    private R_PetReport rPetReportMock;
    private R_Shelter rShelterMock;
    private R_User rUserMock;
    private R_Volunteer rVolunteerMock;

    private AddService service;

    @BeforeEach
    void initService() {
        service = new AddService(rAnimalMock, rAnimalOwnerMock, rInformationMock, rPetReportMock,
                rShelterMock, rUserMock, rVolunteerMock);
    }

    @Test
    void animalSave() {
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