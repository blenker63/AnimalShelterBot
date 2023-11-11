package pro.sky.telegrambot.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.telegrambot.model.*;
import pro.sky.telegrambot.service.ButtonService;
import pro.sky.telegrambot.service.UserService;
import pro.sky.telegrambot.standard.Commands;
import pro.sky.telegrambot.standard.Information;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Component
@Slf4j

public class TelegramBotUpdates extends TelegramLongPollingBot {

    //    @Autowired
//    AbsSender bot;

    private final ButtonService buttonService;
    private final UserService userService;

    public TelegramBotUpdates(@Value("${telegram.bot.token}") String botToken, ButtonService buttonService, UserService userService) {
        super(botToken);
        this.buttonService = buttonService;
        this.userService = userService;
    }

    @Override
    public String getBotUsername() {
        return "${telegram.bot.name}";
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        // Если пришел текст
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userName = update.getMessage().getChat().getFirstName();
            String message = update.getMessage().getText();
            long id = update.getMessage().getChatId();


            if (message.equals(Commands.START.getCommand())) {
                log.info(userName);
                try {
                    if (userService.findAnimalOwnerById(id) == null) {  // если животное взято, напоминание о том что нужно отправить отчет
                        if (userService.findUserAndAnimal(id) == null) {  //проверяем подавал ли кто заявку на животное
                            if (userService.findUserById(id) == null) {     // проверяем первый ли раз зашел ли пользователь
                                userService.saveBotUser(id, userName);      // если нет то сохраняем его в БД
                                execute(buttonService.setButtonStartMenu(id, "Привет " + userName + ", я помогу тебе подобрать домашнего питомца." +
                                        " Предлагаю посмотреть информацию о приютах. "));
                            } else {          // если уже был приветствуем его
                                execute(buttonService.setButtonStartMenu(id, "Привет " + userName + ", мы снова рады тебя приветствовать " +
                                        " в нашем телеграмм чате КотоПес! "));
                            }
                        } else {  // если подавал заявку, напоминаем на какое животное //
                            sendMessage(id, "Привет " + userName + ", вы подавали заявку на:");
                            User us = userService.findUserAndAnimal(id);
                            outInformForAnimal(id, String.valueOf(us.getAnimalId()), true);
                            execute(buttonService.setButtonStartMenu(id, "   Продолжить   просмотр   "));

                        }
                    } else {
                        execute(buttonService.setButtonStartMenu(id, "Привет " + userName + ", желаешь еще посмотреть питомцев " +
                                " в нашем телеграмм чате КотоПес! "));
                        if (userService.findAnimalOwnerById(id).isTrialPeriod()) {                                  // если у владельца еще не закончился испытательный срок
                            execute(buttonService.sendReport(id, "Незабываем отправлять ежедневный отчет!"));
                        }
                    }
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }

            /**********************************************
             *  если первый символ '+' проверяем если это номер
             *  телефона - сохраняем
             **********************************************/
            if (message.startsWith("+")) {
                userService.savePhoneUser(id, message);
                sendMessage(id, "Заявка принята!");
            }

            /**************************************************
             *  если первый символ '#'- пересылаем сообщение
             *****************************************************/
            if (message.startsWith("#")) {
                sendMessage(6515082139L, message);
            }

            /**********************************************
             *  если первый символ 'Питание:' сохраняем данные в отчет
             **********************************************/
            if (message.startsWith("Питание")) {
                if (userService.findPetReportByOwnerIdAndDate(id, LocalDate.now()) == null) {
                    PetReport petReport = new PetReport(id, message, null, false, LocalDate.now());
                    userService.addPetReport(petReport);
                } else {
                    userService.saveDietReport(id, message);
                }
            }

            /**********************************************
             *  если первый символ 'Поведение:' сохраняем данные в отчет
             **********************************************/
            if (message.startsWith("Поведение")) {
                if (userService.findPetReportByOwnerIdAndDate(id, LocalDate.now()) == null) {
                    PetReport petReport = new PetReport(id, null, message, false, LocalDate.now());
                    userService.addPetReport(petReport);
                } else {
                    userService.saveFeelingsReport(id, message);
                }
            }
        }


        /**************************************************
         *  если пришло фото
         *****************************************************/
        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            long id = update.getMessage().getChatId();
            PhotoSize photo = update.getMessage().getPhoto().get(3);
            log.info("Фото пришло");
            GetFile getFile = new GetFile(photo.getFileId());
            var file = execute(getFile);
            File path = new java.io.File("photos/" + id + "_" + photo.getFileUniqueId() + LocalDate.now() + ".jpg");
            log.info(path.getPath());
            downloadFile(file, path);
            if (userService.findPhotoReportByOwnerIdAndDate(id, LocalDate.now()) == null) {                                 // если сегодня фото уже присылалось, перезаписываем путь в БД (один день одно фото)
                try {
                    PhotoReport photoReport = new PhotoReport(id, LocalDate.now(), path.getPath());
                    userService.addPhotoReport(photoReport);
                } catch (Exception e) {
                    log.info("Фото не загружено");
                }
            } else {
                userService.recordDirPhoto(id, path.getPath());
            }
        }


        /****************************************************************************************
         **      Если пришло нажатие кнопки
         ****************************************************************************************/
        if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            String chatNumber = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            /************************************************************************************
             **          Для приюта собак
             ************************************************************************************/
            try {
                if (callData.equals(Commands.DOG_SHELTER.getCommand())) {
                    execute(buttonService.setButtonDogShelterInfo(chatId, "Информация о приюте для собак, схема проезда и правила поведения на территории приюта."));
                }

                if (callData.equals(Commands.DOG_SHELTER_CONTACT_INFO.getCommand())) {
                    sendMessage(chatId, Information.DOG_SHELTER_ADDRESS.getDescription() + "\n " + Information.DOG_SHELTER_WORK_SCHEDULE.getDescription());
                }

                if (callData.equals(Commands.DOG_SHELTER_DATA_SECURITY_PASS.getCommand())) {
                    sendMessage(chatId, Information.DOG_SHELTER_SECURITY_CONTACTS.getDescription());
                }

                if (callData.equals(Commands.DOG_SHELTER_SAFETY_RECOMMENDATIONS.getCommand())) {
                    sendMessage(chatId, Information.SHELTER_SAFETY_PRECAUTIONS.getDescription());
                }

                if (callData.equals(Commands.DOCUMENTS_TAKE_ANIMAL.getCommand())) {
                    sendMessage(chatId, Information.SHELTER_SAFETY_PRECAUTIONS.getDescription());
                }
            } catch (Exception e) {
                log.error("Сообщение " + chatId + "о приюте для собак не отправлено!");
            }
            /*************************************************************
             *      Выводим в лист всех собак
             ************************************************************/
            if (callData.equals(Commands.SHELTER_DOG_ANIMAL.getCommand())) {
                List<Animal> dog = buttonService.countDog();
                /*********************************************************************
                 * Выводим собак в чат и закрепляем за каждым фото кнопку
                 * В планах сделать вывод постранично
                 ******************************************************************/
                for (int i = 0; i < dog.size(); i++) {
                    File f = new File(dog.get(i).getPathToPhoto());
                    var photo = new SendPhoto();
                    photo.setChatId(chatId);
                    photo.setPhoto(new InputFile(f));
                    try {
                        execute(photo);
                        execute(buttonService.setButtonLooKAnimal(chatId, String.valueOf(dog.get(i).getId()), dog.get(i).getName()));
                        //sendMessage(chatId, cat.get(i).getName());
                    } catch (TelegramApiException e) {
                        log.error("Сообщение не отправлено!");
                    }
                }
            }


            /********************************************************************************************
             *      Для приюта кошек
             *******************************************************************************************/
            try {
                if (callData.equals(Commands.CAT_SHELTER.getCommand())) {
                    execute(buttonService.setButtonCatShelterInfo(chatId, "Информация о приюте для кошек, схема проезда и правила поведения на территории приюта."));
                }

                if (callData.equals(Commands.CAT_SHELTER_CONTACT_INFO.getCommand())) {
                    sendMessage(chatId, Information.CAT_SHELTER_ADDRESS.getDescription() + "\n " + Information.CAT_SHELTER_WORK_SCHEDULE.getDescription());
                }

                if (callData.equals(Commands.CAT_SHELTER_DATA_SECURITY_PASS.getCommand())) {
                    sendMessage(chatId, Information.CAT_SHELTER_SECURITY_CONTACTS.getDescription());
                }

                if (callData.equals(Commands.CAT_SHELTER_SAFETY_RECOMMENDATIONS.getCommand())) {
                    sendMessage(chatId, Information.SHELTER_SAFETY_PRECAUTIONS.getDescription());
                }

                if (callData.equals(Commands.DOCUMENTS_TAKE_ANIMAL.getCommand())) {
                    sendMessage(chatId, Information.SHELTER_SAFETY_PRECAUTIONS.getDescription());
                }
            } catch (Exception e) {
                log.error("Сообщение " + chatId + " о приюте для кошек не отправлено!");
            }
            /*************************************************************
             *      Выводим в лист всех котов
             ************************************************************/
            if (callData.equals(Commands.SHELTER_CAT_ANIMAL.getCommand())) {
                List<Animal> cat = buttonService.countCat();

                /*********************************************************************
                 * Выводим котов в чат и закрепляем за каждым фото кнопку
                 * В планах сделать вывод постранично
                 ******************************************************************/
                for (int i = 0; i < cat.size(); i++) {
                    File f = new File(cat.get(i).getPathToPhoto());
                    var photo = new SendPhoto();
                    photo.setChatId(chatId);
                    photo.setPhoto(new InputFile(f));
                    try {
                        execute(photo);
                        execute(buttonService.setButtonLooKAnimal(chatId, String.valueOf(cat.get(i).getId()), cat.get(i).getName()));
                    } catch (TelegramApiException e) {
                        log.error("Сообщение не отправлено!");
                    }
                }
            }
            /**********************************************************
             * Информация об условиях чтобы взять собаку
             *********************************************************/
            if (callData.equals("/information_for_adopting_an_dog")) {
                sendMessage(chatId, Information.RULES_ACQUAINTANCE_ANIMAL.getDescription() + "\n" +
                        Information.DOCUMENT_LIST.getDescription() + "\n" +
                        Information.TRANSPORTATION_RECOMMENDATIONS.getDescription() + "\n" +
                        Information.HOME_YOUNG_ANIMAL_RECOMMENDATIONS.getDescription() + "\n" +
                        Information.HOME_ANIMAL_RECOMMENDATIONS.getDescription() + "\n" +
                        Information.ANIMAL_WITH_DISABILITIES_RECOMMENDATIONS.getDescription() + "\n" +
                        Information.COMMUNICATION_PRIMARY_ADVICE_DOG.getDescription() + "\n" +
                        Information.CYNOLOGIST_LIST.getDescription() + "\n" +
                        Information.REASONS_FOR_REFUSAL_LIST.getDescription() + "\n" +
                        "Также вы можете написать нашим волонтерам поставив '#' в начале сообщения.");
            }
            /**********************************************************
             * Информация об условиях чтобы взять кота
             *********************************************************/
            if (callData.equals("/information_for_adopting_an_cat")) {
                sendMessage(chatId, Information.RULES_ACQUAINTANCE_ANIMAL.getDescription() + "\n" +
                        Information.DOCUMENT_LIST.getDescription() + "\n" +
                        Information.TRANSPORTATION_RECOMMENDATIONS.getDescription() + "\n" +
                        Information.HOME_YOUNG_ANIMAL_RECOMMENDATIONS.getDescription() + "\n" +
                        Information.HOME_ANIMAL_RECOMMENDATIONS.getDescription() + "\n" +
                        Information.ANIMAL_WITH_DISABILITIES_RECOMMENDATIONS.getDescription() + "\n" +
                        Information.REASONS_FOR_REFUSAL_LIST.getDescription() + "\n" +
                        "Также вы можете написать нашим волонтерам поставив '#' в начале сообщения.");
            }

            /**********************************************************
             * действие на кнопку "Отправить отчет", если на текущую дату отчет не был создан
             * он создается в БД
             *********************************************************/
            if (callData.equals("/send_report")) {
                checkingReports();
                sendMessage(chatId, "Для отчета отправьте фото животного, а также два сообщения:\n" +
                        "1. в первом сообщении сначала напишите 'Питание:' и опишите питание за сегодняшний деть.\n" +
                        "2. во втором сообщении сначала напишите 'Поведение:' и опишите как ведет себя питомец " +
                        "на новом месте.");
            }

            outInformForAnimal(chatId, callData, false);
            choiceAnimal(chatId, callData);
        }
    }

    public void sendMessage(Long id, String text) {
        var idStr = String.valueOf(id);
        var sendMessage = new SendMessage(idStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Сообщение не отправлено!");
        }
    }

    /********************************************************************************
     *  Обработка на нажатие кнопки "Подробно"
     * @param chatId Id пользователя
     * @param call_data принимаем кал-бек, если это цифры значит Id животного
     * @param flag либо поиск животного, либо напоминание о том что была подана заявка на этого животного
     ********************************************************************************/
    public void outInformForAnimal(long chatId, String call_data, boolean flag) {
        try {
            if (call_data.matches("[0-9]")) {
                Long date = Long.valueOf(call_data);
                Animal animal = buttonService.findCallBackByAnimal(date);
                File f = new File(animal.getPathToPhoto());
                var photo = new SendPhoto();
                photo.setChatId(chatId);
                photo.setPhoto(new InputFile(f));

                if (flag) {
                    execute(photo);
                    sendMessage(chatId, "Имя: " + animal.getName() + "\nВозраст: " + animal.getAge() + " месяц(а)\nПорода: " + animal.getBreed());
                } else {
                    execute(photo);
                    execute(buttonService.choiceAnimal(chatId, String.valueOf(animal.getId()), "Имя: " + animal.getName() + "\nВозраст: " + animal.getAge() + " месяц(а)\nПорода: " + animal.getBreed()));
                }

            }
        } catch (TelegramApiException e) {
            log.error("outInformForAnimal не отработало!");
        }
    }

    /******************************************************************************
     *  Принимаем заявку на животного и сохраняем пользователя и Id животного в БД "users"
     * @param chatId Id пользователя подавшего заявка
     * @param call_back принимаем кал-бек, если начинается с букв "ID", отсекаем их, за ними следует Id животного
     **********************************************************************************/
    public void choiceAnimal(long chatId, String call_back) {
        if (call_back.startsWith("ID")) {
            String call = call_back.substring(2);
            String animalType = buttonService.findAnimalTypeById(Long.valueOf(call));
            long value = Long.valueOf(call);
            userService.saveUser(chatId, value);
            log.info(" Заявка принята!");
            try {
                execute(buttonService.informationForAdoptingAnAnimal(chatId, animalType, "Заявка принята!\nНапишите нам в сообщении свой номер телефона в формате: +7**********, что бы мы смогли с вами " +
                        "связаться, а также ознакомтесь с тем, что нужно сделать прежде чем забрать питомца домой."));
            } catch (TelegramApiException e) {
                log.error("Сообщение не отправлено!");
            }
        }
    }

    /************************************************************
     * Проверка отчетов каждый день в 21:00
     ***********************************************************/
    @Scheduled(cron = "* * 21 * * *")
    public void checkingReports() {
        LocalDate dateNow = LocalDate.now();
        List<AnimalOwner> animalOwners = userService.findAllByTrialPeriodTrue();                                                          // список всех владельцев
        for (AnimalOwner animalOwner : animalOwners) {
            if (userService.findPetReportByOwnerIdAndDate(animalOwner.getId(), dateNow) == null) {                               // если сегодня отчетов не было
                try {                                                                                                           // напоминаем владельцу
                    execute(buttonService.sendReport(animalOwner.getId(), Information.BOT_OWNER_MESSAGE.getDescription()));
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }
            if (userService.checkingLastDateReports(animalOwner.getId()) == null) {                                              // если владелец еще не оставлял отчетов
                Period period = animalOwner.getDate().until(dateNow);                                                            // проверяем как давно он взял питомца
                if (period.getDays() >= 2) {                                                                                     // если больше двух дней назад
                    //sendMessage(Id-volunteer, Information.BOT_VOLUNTEER_MESSAGE.getDescription()+animalOwner.toString());     // отправляем предупреждение волонтеру
                    log.info("Отчетов не было больше 2-х дней" + animalOwner.toString());
                }
            } else {
                PetReport petReport = userService.checkingLastDateReports(animalOwner.getId());                                 // если отчет есть, забираем по последней дате добавления
                LocalDate reportDate = petReport.getDate();                                                                     // извлекаем дату
                if (!(petReport.getDate().equals(dateNow))) {                                                                    // сверяем с текущей датой, ни сегодня ли прислан отчет
                    Period period = reportDate.until(dateNow);                                                                  // если нет проверяем как давно
                    log.info(String.valueOf(period.getDays()));
                    if (period.getDays() >= 2) {                                                                                 // если два или более дня назад
                        //sendMessage(Id-volunteer, Information.BOT_VOLUNTEER_MESSAGE.getDescription()+animalOwner.toString()); // предупреждаем волонтера
                        log.info("Отчетов не было больше 2-х дней");
                    }
                } else {
                    if (petReport.getDiet() == null || petReport.getFeelings() == null) {
                        sendMessage(animalOwner.getId(), " Пришлите текстовый отчет!");
                    }
                    if (userService.findPhotoReportByOwnerIdAndDate(animalOwner.getId(), LocalDate.now()) == null) {
                        sendMessage(animalOwner.getId(), " Пришлите фото отчет!");
                    }
                }
            }
        }
    }

    /**
     * если испытательный срок закончился
     *
     * @param ownerId владельца питомца
     * @return инфо владельца питомца
     */
    public AnimalOwner trialPeriodOff(long ownerId) {
        sendMessage(ownerId, "Дорогой усыновитель, мы поздравляем вас с успешны прохождением испытательного срока! (и т.д...)");
        return userService.trialPeriodOff(ownerId);
    }

    /**
     * если испытательный срок продлен, оповещаем без каких либо записей
     *
     * @param ownerId владельца питомца
     * @param period  оповещаем на сколько дней продляем
     * @return инфо владельца питомца
     */
    public AnimalOwner trialPeriodNotFinished(long ownerId, int period) {
        sendMessage(ownerId, "Дорогой усыновитель, вам продлен испытательный срок на " + period + " дней!");
        return userService.findAnimalOwnerById(ownerId);
    }


}


