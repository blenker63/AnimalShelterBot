package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.Shelter;
import pro.sky.telegrambot.service.AddService;

/**
 * класс содержит эндпойнты для внесения информации в базу данных
 */
@RequestMapping("/shelter")
@RestController
public class InfoController {
    private final AddService addService;

    public InfoController(AddService addService) {
        this.addService = addService;
    }

    @Operation(
            summary = "внесение информации о приюте",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные о приюте внесены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Shelter.class))
                            )
                    )
            }
    )
    @PostMapping
    public Shelter ShelterSave(@RequestBody Shelter shelter) {
        return addService.ShelterSave(shelter);
    }
}
