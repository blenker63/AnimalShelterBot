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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddServiceTest {
    @Mock
    private R_Animal rAnimalMock;
    @Mock
    private R_AnimalOwner rAnimalOwnerMock;
    @Mock
    private R_PetReport rPetReportMock;
    @Mock
    private R_Shelter rShelterMock;
    @Mock
    private R_Volunteer rVolunteerMock;

    private AddService service;

    @BeforeEach
    void initService() {
        service = new AddService(rAnimalMock, rAnimalOwnerMock, rPetReportMock,
                rShelterMock, rVolunteerMock);
    }

    @Test
    void animalSaveTest() {
        var animal = new Animal("cat", "will", 3, "maincoon");
        when(rAnimalMock.save(animal)).thenReturn(animal);
        assertEquals(animal, service.AnimalSave("cat", "will", 3, "maincoon"));
        verify(rAnimalMock, times(1)).save(animal);
    }

    @Test
    void animalOwnerSaveTest() {
        var animalOwner = new AnimalOwner("leo", "88009007060", "mail@mail.ru", true, LocalDate.now());
        when(rAnimalOwnerMock.save(animalOwner)).thenReturn(animalOwner);
        assertEquals(animalOwner, service.AnimalOwnerSave(animalOwner));
    }


//    @Test
//    void petReportSaveTest() {
//        var petReport = new PetReport("diet", "feelings", true, LocalDateTime.now());
//        when(rPetReportMock.save(petReport)).thenReturn(petReport);
//        assertEquals(petReport, service.PetReportSave("diet", "feelings", true));
//    }

    @Test
    void shelterSaveTest() {
        var shelter = new Shelter("catShelter", "Kitty", "Tula", "very good");
        when(rShelterMock.save(shelter)).thenReturn(shelter);
        assertEquals(shelter, service.ShelterSave("catShelter", "Kitty", "Tula", "very good"));
    }


    @Test
    void volunteerSaveTest() {
        var volunteer = new Volunteer("nick", "88007007070");
        when(rVolunteerMock.save(volunteer)).thenReturn(volunteer);
        assertEquals(volunteer, service.VolunteerSave("nick", "88007007070"));
    }
}