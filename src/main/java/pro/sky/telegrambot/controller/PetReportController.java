package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.service.AddService;
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
    private final AddService addService;

    public PetReportController(PetReportService petReportService, AddService addService) {
        this.petReportService = petReportService;
        this.addService = addService;
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
            summary = "вывод отчетов за выбранную дату",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "выведены отчеты за дату",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = PetReport.class))
                            )
                    )
            }
    )
    @GetMapping("/date")
    public ResponseEntity<Collection<PetReport>> readPetReportDate(@Parameter(description = "Дата отчета",
                                                                              example = "2023-09-20")
                                                                   @RequestParam String date) {
        LocalDate dateNew = LocalDate.parse(date);
        return ResponseEntity.ok(petReportService.readPetReportDate(LocalDate.from(dateNew)));
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
    public ResponseEntity<Collection<PetReport>> readById(@RequestParam long id) {
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
    public ResponseEntity<Collection<PetReport>> readControl(@RequestParam boolean control) {
        return ResponseEntity.ok(petReportService.readControl(control));
    }
//    @PostMapping("/pet-report/{diet}/{feelings}/{check}")
//    public void PetReportSave(@PathVariable long id,
//                              @PathVariable String diet,
//                              @PathVariable String feelings,
//                              @PathVariable boolean check) {
//        addService.PetReportSave(id, diet, feelings, check);
//    }
}
