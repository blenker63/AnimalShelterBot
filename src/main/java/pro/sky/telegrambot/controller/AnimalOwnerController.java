package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.listener.TelegramBotUpdates;
import pro.sky.telegrambot.model.AnimalOwner;
import pro.sky.telegrambot.service.AddService;
import pro.sky.telegrambot.service.UserService;

/**
 * клас содержит эндпойнты для внесения информации в базу данных по взявшим животных из приюта
 */
@RestController
@RequestMapping("/animal-owner")
public class AnimalOwnerController {
    private final AddService addService;
    private final UserService userService;
    private final TelegramBotUpdates telegramBotUpdates;


    public AnimalOwnerController(AddService addService, UserService userService, TelegramBotUpdates telegramBotUpdates) {
        this.addService = addService;
        this.userService = userService;
        this.telegramBotUpdates = telegramBotUpdates;
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
    @PostMapping
    public AnimalOwner AnimalOwnerSave(@RequestBody AnimalOwner animalOwner) {
        return addService.AnimalOwnerSave(animalOwner);

    }

    @GetMapping("/{owner_id}")
    public String trialPeriodAnimalOwner(@PathVariable Long owner_id){
        return userService.trialPeriodAnimalOwner(owner_id);
    }

    @GetMapping("/trial-period-off/{owner_id}")
    public AnimalOwner trialPeriodOff(@PathVariable Long owner_id){
        return telegramBotUpdates.trialPeriodOff(owner_id);
    }

    @GetMapping("/trial-period-not-finish/{owner_id}/{period}")
    public AnimalOwner trialPeriodNotFinished(@PathVariable Long owner_id, @PathVariable int period){
        return telegramBotUpdates.trialPeriodNotFinished(owner_id, period);
    }

}
