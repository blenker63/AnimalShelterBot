package pro.sky.telegrambot.service;

import com.fasterxml.jackson.databind.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PetReportServiceTest {
    @Mock
    private R_PetReport rPetReportMock;
    private PetReportService service;

    @BeforeEach
    void setUp() {
        service = new PetReportService(rPetReportMock);
    }

    @Test

    void readAllReportTest() {
        var report1 = new PetReport(1L, "meat", "good",  true, LocalDate.now());
        var report2 = new PetReport(2L, "chick", "verygood",  true, LocalDate.now());
        List<PetReport> list = List.of(report1, report2);
        when(rPetReportMock.findAll()).thenReturn(list);
        assertEquals(2, service.readAllReport().size());
        verify(rPetReportMock, times(1)).findAll();
    }

    @Test
    void readPetReportDateTest() {
        var report1 = new PetReport(1L, "meat", "good",  true, LocalDate.now());
        var report2 = new PetReport(2L, "chick", "verygood",  true, LocalDate.now());
        List<PetReport> list = List.of(report1, report2);
        when(rPetReportMock.findByDate(LocalDate.now())).thenReturn(list);
        assertEquals(2,service.readPetReportDate(LocalDate.now()).size());
        verify(rPetReportMock, times(1)).findByDate(LocalDate.now());
    }

    @Test
    void readByIdTest() {
        var report1 = new PetReport(1L, "meat", "good",  true, LocalDate.now());
        List<PetReport> list = List.of(report1);
        when(rPetReportMock.findById(1L)).thenReturn(list);
        assertEquals(1, service.readById(1L).size());
        verify(rPetReportMock, times(1)).findById(1L);
    }

    @Test
    void readControlTest() {
        var report1 = new PetReport(1L, "meat", "good",  true, LocalDate.now());
        var report2 = new PetReport(1L, "meat", "good",  true, LocalDate.now());
        List<PetReport> list = List.of(report1, report2);
        when(rPetReportMock.findByControl(true)).thenReturn(list);
        assertEquals(2, service.readControl(true).size());
        verify(rPetReportMock, times(1)).findByControl(true);
    }
}