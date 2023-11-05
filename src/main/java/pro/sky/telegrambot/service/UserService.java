package pro.sky.telegrambot.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.*;
import pro.sky.telegrambot.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
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
    public BotUser saveBotUser(long id, String name) {
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        BotUser user = new BotUser(id, name, date);
        return botUserRepository.save(user);
    }

    /*********************************************************************
     *   Сохраняем пользователя сделавшего заявку
     * @param id id пользователя
     * @param animalId id животного
     *********************************************************************/
    public User saveUser(long id, long animalId) {
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        BotUser botUser = botUserRepository.findBotUserByUserId(id);
        User user = new User(id, botUser.getName(), date, animalId);
        return userRepository.save(user);
    }

    /************************************************************************
     *  Сохраняет номер телефона пользователя, отправленного им в сообщении
     * @param chatId пользователь
     * @param message сообщение
     ***********************************************************************/
    public void savePhoneUser(long chatId, String message) {
        String telephone = null;
        Pattern pattern = Pattern.compile("[0-9\\+]{12}+");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            telephone = message.substring(matcher.start(), matcher.end());
        }
        userRepository.savePhone(chatId, telephone);
    }

    /**
     * поиск всех владельцев животных
     *
     * @return список владельцев
     */
    public List<AnimalOwner> allAnimalOwner() {
        List<AnimalOwner> animalOwners = animalOwnerRepository.findAll();
        return animalOwners;
    }

    /**
     * поиск заходившего в бот пользователя
     *
     * @param id пользователя
     * @return инфо о пользователе
     */
    public BotUser findUserById(long id) {
        BotUser botUser = botUserRepository.findBotUserByUserId(id);
        return botUser;
    }

    /**
     * поиск пользователя подавшего заявку
     *
     * @param id пользователя
     * @return инфо о пользователе
     */
    public User findUserAndAnimal(long id) {
        User user = userRepository.findUserByChatId(id);
        return user;
    }

    /**
     * поиск пользователя взявшего питомца
     *
     * @param chatId пользователя
     * @return инфо о пользователе
     */
    public AnimalOwner findAnimalOwnerById(long chatId) {
        AnimalOwner animalOwner = animalOwnerRepository.findAnimalOwnerById(chatId);
        return animalOwner;
    }

    /**
     * поиск отчета по дате добавления
     *
     * @param ownerId пользователя
     * @param date    дата добавления отчета
     * @return отчет
     */
    public PetReport findPetReportByOwnerIdAndDate(long ownerId, LocalDate date) {
        PetReport petReport = petReportRepository.findPetReportByOwnerIdAndDate(ownerId, date);
        return petReport;
    }

    /**
     * сохранение рациона питания в отчет
     *
     * @param id   владельца питомца
     * @param diet рациона питания
     */
    public void saveDietReport(long id, String diet) {
        PetReport petReport = petReportRepository.findPetReportByOwnerIdAndDate(id, LocalDate.now());
        petReportRepository.saveDiet(petReport.getId(), diet);
    }

    /**
     * сохранение поведения питомца в отчет
     *
     * @param id       владельца питомца
     * @param feelings поведение питомца
     */
    public void saveFeelingsReport(long id, String feelings) {
        PetReport petReport = petReportRepository.findPetReportByOwnerIdAndDate(id, LocalDate.now());
        petReportRepository.saveFeelings(petReport.getId(), feelings);
    }

    /**
     * добавление отчета в БД
     *
     * @param petReport отчет
     * @return отчет
     */
    public PetReport addPetReport(PetReport petReport) {
        petReportRepository.save(petReport);
        return petReport;
    }

    /**
     * выбор отчета по последней дате добавления
     *
     * @param animalOwner_id владельца питомца
     * @return отчет
     */
    public PetReport checkingLastDateReports(long animalOwner_id) {
        return petReportRepository.checkingLastDateReports(animalOwner_id);
    }

    /**
     * добавление фото отчета в БД
     *
     * @param photoReport фото отчет
     * @return фото отчет
     */
    public PhotoReport addPhotoReport(PhotoReport photoReport) {
        photoReportRepository.save(photoReport);
        return photoReport;
    }

    /**
     * поиск фото в отчете по владельцу и дате добавления
     *
     * @param owner_id владельца
     * @param date     дата
     * @return фото отчет
     */
    public PhotoReport findPhotoReportByOwnerIdAndDate(Long owner_id, LocalDate date) {
        return photoReportRepository.findPhotoReportByOwnerIdAndDate(owner_id, date);
    }

    /**
     * перезапись фото в БД
     *
     * @param ownerId пользователя
     * @param dir     путь где хранится фото
     * @return фото отчет
     */
    public PhotoReport recordDirPhoto(long ownerId, String dir) {
        return photoReportRepository.recordDirPhoto(ownerId, dir);
    }
    /**
     * перезапись фото в БД
     * @return список все х пользователей
     * */
    public Collection<User> findAll() {
        return Collections.unmodifiableCollection(userRepository.findAll());
    }

}
