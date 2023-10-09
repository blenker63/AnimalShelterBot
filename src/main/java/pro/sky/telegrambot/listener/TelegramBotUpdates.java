package pro.sky.telegrambot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.service.ButtonService;
import pro.sky.telegrambot.service.UserService;
import pro.sky.telegrambot.standard.Commands;
import pro.sky.telegrambot.standard.Information;

import java.io.File;
import java.util.List;


@Component
@Slf4j
public class TelegramBotUpdates extends TelegramLongPollingBot {

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


    @Override
    public void onUpdateReceived(Update update) {
        // Если пришел текст
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userName = update.getMessage().getChat().getFirstName();
            String message = update.getMessage().getText();
            long id = update.getMessage().getChatId();


            if (message.equals(Commands.START.getCommand())) {
                log.info(userName);
                if (userService.findUserAndAnimal(id) == null) {  //проверяем подавал ли кто заявку на животное
                    if (userService.findUserById(id) == null) {     // проверяем первый ли раз зашел ли пользователь
                        userService.saveBotUser(id, userName);      // если нет то сохраняем его в БД
                        try {
                            execute(buttonService.setButtonStartMenu(id, "Привет " + userName + ", я помогу тебе подобрать домашнего питомца." +
                                    " Предлагаю посмотреть информацию о приютах. "));
                        } catch (TelegramApiException e) {
                            log.error("Сообщение не отправлено!");
                        }
                    } else {          // если уже был приветствуем его
                        try {
                            execute(buttonService.setButtonStartMenu(id, "Привет " + userName + ", мы снова рады тебя приветствовать " +
                                    " в нашем телеграмм чате КотоПес! "));
                        } catch (TelegramApiException e) {
                            log.error("Сообщение не отправлено!");
                        }
                    }
                }else {  // если подавал заявку, напоминаем на какое животное //
                    sendMessage(id, "Привет "+userName+", вы подавали заявку на:");
                    User us = userService.findUserAndAnimal(id);
                    outInformForAnimal(id, String.valueOf(us.getAnimalId()), true);
                    try {
                        execute(buttonService.setButtonStartMenu(id, "   Продолжить   просмотр   "));
                    } catch (TelegramApiException e) {
                        log.error("Сообщение не отправлено!");
                    }
                }
            }

            /**********************************************
             *  если первый символ '+' проверяем если это номер
             *  телефона - сохраняем
             **********************************************/
            if (message.startsWith("+")){
                userService.savePhoneUser(id, message);
                sendMessage(id, "Заявка принята!");
            }

            /**************************************************
             *  если первый символ '#'- пересылаем сообщение
             *****************************************************/
            if (message.startsWith("#")){
                sendMessage(6515082139L, message);
            }
        }



        /****************************************************************************************
         **      Если пришло нажатие кнопки
         ****************************************************************************************/
        if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            String chat_id = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            long chatId = update.getCallbackQuery().getMessage().getChatId();
             /************************************************************************************
             **          Для приюта собак
             ************************************************************************************/
            if (call_data.equals(Commands.DOG_SHELTER.getCommand())) {
                try {
                    execute(buttonService.setButtonDogShelterInfo(chatId, "Информация о приюте для собак, схема проезда и правила поведения на территории приюта."));
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }

            if (call_data.equals(Commands.DOG_SHELTER_CONTACT_INFO.getCommand())) {
                try {
                    sendMessage(chatId, Information.DOG_SHELTER_ADDRESS.getDescription() + "\n " + Information.DOG_SHELTER_WORK_SCHEDULE.getDescription());
                } catch (Exception e) {
                    log.error("Сообщение " + chatId +" " +  Information.DOG_SHELTER_ADDRESS.getDescription() + " не отправлено!");
                }
            }

            if (call_data.equals(Commands.DOG_SHELTER_DATA_SECURITY_PASS.getCommand())) {
                try {
                    sendMessage(chatId, Information.DOG_SHELTER_SECURITY_CONTACTS.getDescription());
                } catch (Exception e) {
                    log.error("Сообщение " + chatId +" " +  Information.DOG_SHELTER_SECURITY_CONTACTS.getDescription() + " не отправлено!");
                }
            }

            if (call_data.equals(Commands.DOG_SHELTER_SAFETY_RECOMMENDATIONS.getCommand())) {
                try {
                    sendMessage(chatId, Information.SHELTER_SAFETY_PRECAUTIONS.getDescription());
                } catch (Exception e) {
                    log.error("Сообщение " + chatId +" " +  Information.SHELTER_SAFETY_PRECAUTIONS.getCommand() + " не отправлено!");
                }
            }

            if (call_data.equals(Commands.DOCUMENTS_TAKE_ANIMAL.getCommand())) {
                try {
                    sendMessage(chatId, Information.SHELTER_SAFETY_PRECAUTIONS.getDescription());
                } catch (Exception e) {
                    log.error("Сообщение " + chatId +" " +  Information.SHELTER_SAFETY_PRECAUTIONS.getCommand() + " не отправлено!");
                }
            }
            /*************************************************************
             *      Выводим в лист всех собак
             ************************************************************/
            if (call_data.equals(Commands.SHELTER_DOG_ANIMAL.getCommand())) {
                List<Animal> dog = buttonService.countDog();
                /*********************************************************************
                 * Выводим собак в чат и закрепляем за каждым фото кнопку
                 * В планах сделать вывод постранично
                 ******************************************************************/
                for (int i=0; i<dog.size(); i++) {
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
            if (call_data.equals(Commands.CAT_SHELTER.getCommand())) {
                try {
                    execute(buttonService.setButtonCatShelterInfo(chatId, "Информация о приюте для кошек, схема проезда и правила поведения на территории приюта."));
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }

            if (call_data.equals(Commands.CAT_SHELTER_CONTACT_INFO.getCommand())) {
                try {
                    sendMessage(chatId, Information.CAT_SHELTER_ADDRESS.getDescription() + "\n " + Information.CAT_SHELTER_WORK_SCHEDULE.getDescription());
                } catch (Exception e) {
                    log.error("Сообщение " + chatId +" " +  Information.CAT_SHELTER_ADDRESS.getDescription() + " не отправлено!");
                }
            }

            if (call_data.equals(Commands.CAT_SHELTER_DATA_SECURITY_PASS.getCommand())) {
                try {
                    sendMessage(chatId, Information.CAT_SHELTER_SECURITY_CONTACTS.getDescription());
                } catch (Exception e) {
                    log.error("Сообщение " + chatId +" " +  Information.DOG_SHELTER_SECURITY_CONTACTS.getDescription() + " не отправлено!");
                }
            }

            if (call_data.equals(Commands.CAT_SHELTER_SAFETY_RECOMMENDATIONS.getCommand())) {
                try {
                    sendMessage(chatId, Information.SHELTER_SAFETY_PRECAUTIONS.getDescription());
                } catch (Exception e) {
                    log.error("Сообщение " + chatId +" " +  Information.SHELTER_SAFETY_PRECAUTIONS.getCommand() + " не отправлено!");
                }
            }

            if (call_data.equals(Commands.DOCUMENTS_TAKE_ANIMAL.getCommand())) {
                try {
                    sendMessage(chatId, Information.SHELTER_SAFETY_PRECAUTIONS.getDescription());
                } catch (Exception e) {
                    log.error("Сообщение " + chatId +" " +  Information.SHELTER_SAFETY_PRECAUTIONS.getCommand() + " не отправлено!");
                }
            }
            /*************************************************************
             *      Выводим в лист всех котов
             ************************************************************/
            if (call_data.equals(Commands.SHELTER_CAT_ANIMAL.getCommand())) {
                List<Animal> cat = buttonService.countCat();

                /*********************************************************************
                 * Выводим котов в чат и закрепляем за каждым фото кнопку
                 * В планах сделать вывод постранично
                 ******************************************************************/
                for (int i=0; i<cat.size(); i++) {
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

            outInformForAnimal(chatId, call_data, false);
            choiceAnimal(chatId, call_data);
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
    public void outInformForAnimal(long chatId, String call_data, boolean flag){
        if (call_data.matches("[0-9]")){
            Long date = Long.valueOf(call_data);
            Animal animal = buttonService.findCallBackByAnimal(date);
            File f = new File(animal.getPathToPhoto());
            var photo = new SendPhoto();
            photo.setChatId(chatId);
            photo.setPhoto(new InputFile(f));
            if (flag){
                try {
                    execute(photo);
                    sendMessage(chatId, "Имя: "+animal.getName()+"\nВозраст: "+animal.getAge()+" месяц(а)\nПорода: "+animal.getBreed());
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }else {
                try {
                    execute(photo);
                    execute(buttonService.choiceAnimal(chatId, String.valueOf(animal.getId()), "Имя: "+animal.getName()+"\nВозраст: "+animal.getAge()+" месяц(а)\nПорода: "+animal.getBreed()));
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }
        }
    }

    /******************************************************************************
     *  Принимаем заявку на животного и сохраняем пользователя и Id животного в БД "users"
     * @param chatId Id пользователя подавшего заявка
     * @param call_back принимаем кал-бек, если начинается с букв "ID", отсекаем их, за ними следует Id животного
     */
    public void choiceAnimal(long chatId, String call_back){
        if (call_back.startsWith("ID")){
           String call = call_back.substring(2);
           long value = Long.valueOf(call);
           userService.saveUser(chatId, value);
           sendMessage(chatId, "Напишите нам в сообщении свой номер телефона в формате: +7**********, что бы мы смогли с вами связаться.");
           log.info(" Заявка принята!");
        }
    }
}


