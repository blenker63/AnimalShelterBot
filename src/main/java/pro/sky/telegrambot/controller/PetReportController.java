package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.service.PetReportService;

import java.time.LocalDate;
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
    @Operation(
            summary = "вывод списка всех отчетов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "выведены все отчеты",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = PetReport.class))
                            )
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<Collection<PetReport>> readAllReport() {
        return ResponseEntity.ok(petReportService.readAllReport());
    }
    @Operation(
            summary = "вывод отчетов за выбранный период",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "выведены отчеты за период",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = PetReport.class))
                            )
                    )
            }
    )
//    @GetMapping("/between")
//    public ResponseEntity<Collection<PetReport>> readPetReportDateBetween(@RequestParam LocalDateTime begin,
//                                                                          @RequestParam LocalDateTime end) {
//        return ResponseEntity.ok(petReportService.readPetReportDateBetween(begin, end));
//    }
    @GetMapping("/between")
    public ResponseEntity<Collection<PetReport>> readPetReportDateBetween(@RequestParam LocalDate date) {
        return ResponseEntity.ok(petReportService.readPetReportDate(date));
    }
    @Operation(
            summary = "вывод отчетов по владельцу животного",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "выведены отчеты по владельцу животного",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = PetReport.class))
                            )
                    )
            }
    )
    @GetMapping("/id")
    public ResponseEntity<Collection<PetReport>> readById(long id) {
        return ResponseEntity.ok(petReportService.readById(id));
    }
    @Operation(
            summary = "вывод отчетов по статусу испытательного срока",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "выведены отчеты по статусу испытательного срока",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = PetReport.class))
                            )
                    )
            }
    )
    @GetMapping("/check")
    public ResponseEntity<Collection<PetReport>> readCheck(boolean check) {
        return ResponseEntity.ok(petReportService.readCheck(check));
    }
}
