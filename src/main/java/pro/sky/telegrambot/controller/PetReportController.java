package pro.sky.telegrambot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.service.PetReportService;

import java.util.Collection;

/**
 * класс содержит эндпойнты для работы с отчетами волонтером
 */
@RequestMapping("/pet-report")
@RestController

public class PetReportController {

    private final PetReportService petReportService;

    public PetReportController(PetReportService petReportService) {
        this.petReportService = petReportService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<PetReport>> readAllReport() {
        return ResponseEntity.ok(petReportService.readAllReport());
    }

}
