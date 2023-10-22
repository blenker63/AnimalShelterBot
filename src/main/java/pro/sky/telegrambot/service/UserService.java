package pro.sky.telegrambot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.*;
import pro.sky.telegrambot.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class UserService {

    private final R_BotUser botUserRepository;
    private final R_User userRepository;
    private final R_AnimalOwner animalOwnerRepository;
    private final R_PetReport petReportRepository;

    private final R_PhotoReport photoReportRepository;

    public UserService(R_BotUser botUserRepository, R_User userRepository, R_AnimalOwner animalOwnerRepositiry, R_PetReport petReportRepository, R_PhotoReport photoReportRepository) {
        this.botUserRepository = botUserRepository;
        this.userRepository = userRepository;
        this.animalOwnerRepository = animalOwnerRepositiry;
        this.petReportRepository = petReportRepository;
        this.photoReportRepository = photoReportRepository;
    }

    /***************************************************************
     *   Сохраняем в БД нового пользователя
     * @param id  id пользователя
     * @param name имя пользователя
     ***************************************************************/
    public BotUser saveBotUser(long id, String name){
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        BotUser user = new BotUser(id, name, date);
        return botUserRepository.save(user);
    }

    /*********************************************************************
     *   Сохраняем пользователя сделавшего заявку
     * @param id id пользователя
     * @param animalId id животного
     *********************************************************************/
    public User saveUser(long id, long animalId){
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        BotUser botUser =  botUserRepository.findBotUserByUserId(id);
        User user = new User(id, botUser.getName(), date, animalId);
        return userRepository.save(user);
    }

    /************************************************************************
     *  Сохраняет номер телефона пользователя, отправленного им в сообщении
     * @param chatId пользователь
     * @param message сообщение
     ***********************************************************************/
    public void savePhoneUser(long chatId, String message){
        String telephone = null;
        Pattern pattern = Pattern.compile("[0-9\\+]{12}+");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            telephone = message.substring(matcher.start(), matcher.end());
        }
        userRepository.savePhone(chatId, telephone);
    }

    public List<AnimalOwner> allAnimalOwner(){
        List<AnimalOwner> animalOwners = animalOwnerRepository.findAll();
        return animalOwners;
    }

    public BotUser findUserById(long id){
        BotUser botUser = botUserRepository.findBotUserByUserId(id);
        return botUser;
    }

    public User findUserAndAnimal(long id){
        User user = userRepository.findUserByChatId(id);
        return user;
    }

    public AnimalOwner findAnimalOwnerById(long chatId){
        AnimalOwner animalOwner = animalOwnerRepository.findAnimalOwnerById(chatId);
        return animalOwner;
    }

    public PetReport findPetReportByOwnerIdAndDate(long ownerId, LocalDate date){
        PetReport petReport = petReportRepository.findPetReportByOwnerIdAndDate(ownerId, date);
        return petReport;
    }

    public void saveDietReport(long id, String diet){
        PetReport petReport = petReportRepository.findPetReportByOwnerIdAndDate(id, LocalDate.now());
        petReportRepository.saveDiet(petReport.getId(), diet);
    }

    public void saveFeelingsReport(long id, String feelings){
        PetReport petReport = petReportRepository.findPetReportByOwnerIdAndDate(id, LocalDate.now());
        petReportRepository.saveFeelings(petReport.getId(), feelings);
    }

    public PetReport addPetReport(PetReport petReport){
        petReportRepository.save(petReport);
        return petReport;
    }

    public PhotoReport addPhotoReport(PhotoReport photoReport){
        photoReportRepository.save(photoReport);
        return photoReport;
    }

    public PetReport checkingLastDateReports(long animalOwner_id){
        return petReportRepository.checkingLastDateReports(animalOwner_id);
    }

}
