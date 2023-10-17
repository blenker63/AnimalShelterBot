package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.service.AddService;

/**
 * класс содержит эндпойнты для внесения информации в базу данных по животным
 */
public class AnimalsController {

    private final AddService addService;

    public AnimalsController(AddService addService) {
        this.addService = addService;
    }

    @Operation(
            summary = "внесение информации по животному в базу данных приюта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные по животному внесены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Animal.class))
                            )
                    )
            }
    )
    @PostMapping("/animal/{animalType}/{name}/{age}/{breed}")
    public void AnimalSave(@PathVariable String animalType, @PathVariable String name, @PathVariable int age, @PathVariable String breed) {
        addService.AnimalSave(animalType, name, age, breed);
    }

    @Operation(
            summary = "Отчет по животному взятому из приюта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "отчет заполнен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = PetReport.class))
                            )
                    )
            }
    )
    @PostMapping("/pet-report/{diet}/{feelings}/{check}")
    public void PetReportSave(@PathVariable String diet, @PathVariable String feelings, @PathVariable boolean check) {
        addService.PetReportSave(diet, feelings, check);
    }

}
