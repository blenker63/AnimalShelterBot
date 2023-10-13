package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.AnimalOwner;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.model.Shelter;
import pro.sky.telegrambot.service.AddService;

/**
 * класс содержит эндпойнты для внесения информации в базу данных
 */
@RequestMapping("/add")
@RestController
public class FillingDataBaseController {

    private final AddService addService;

    public FillingDataBaseController(AddService addService) {
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
    @PostMapping("/animal-owner/{name}/{phoneNumber}/{eMail}/{trialPeriod}")
    public void AnimalOwnerSave(@PathVariable String name, @PathVariable String phoneNumber, @PathVariable String eMail, @PathVariable boolean trialPeriod) {
        addService.AnimalOwnerSave(name, phoneNumber, eMail, trialPeriod);

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
    @PostMapping("/shelter/{shelterType}/{shelterName}/{address}/{information}")
    public void ShelterSave(@PathVariable String shelterType, @PathVariable String shelterName, @PathVariable String address, @PathVariable String information) {
        addService.ShelterSave(shelterType, shelterName, address, information);
    }

//    @PostMapping("/user/{name}/{phoneNumber}/{eMail}/{chatId}/{localDateTime}")
//    public void UserSave(@PathVariable String name, @PathVariable String phoneNumber,
//                         @PathVariable Long chatId, @PathVariable LocalDateTime localDateTime){
//        addService.UserSave(name, phoneNumber, chatId, localDateTime);
//    }

    @PostMapping("/{s_id}/{id}")
    public void AS(@PathVariable long s_id, @PathVariable long id) {
        addService.anSet(s_id, id);
    }
}
