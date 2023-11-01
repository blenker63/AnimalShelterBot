package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pro.sky.telegrambot.model.AnimalOwner;
import pro.sky.telegrambot.service.AddService;

/**
 * класс содержит эндпойнты для внесения информации в базу данных по взявшим животных из приюта
 */
public class AnimalOwnerController {
    private final AddService addService;


    public AnimalOwnerController(AddService addService) {
        this.addService = addService;
    }

    @Operation(
            summary = "внесение информации по владельцу взявшего животное из приюта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные по владельцу внесены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AnimalOwner.class))
                            )
                    )
            }
    )
    @PostMapping("/animal-owner")
    public void AnimalOwnerSave(@RequestBody AnimalOwner animalOwner) {
        addService.AnimalOwnerSave(animalOwner);

    }
}
