package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.standard.Commands;

/**
 * класс-сервис содержащий методы по обработке всех команд телеграмм бота
 */
@Service
public class BotService {
    private final Logger logger = LoggerFactory.getLogger(BotService.class);
    private final TelegramBot telegramBot;
    private final KeyboardService keyboardService;

    public BotService(TelegramBot telegramBot, KeyboardService keyboardService) {
        this.telegramBot = telegramBot;
        this.keyboardService = keyboardService;
    }

    /**
     * метод осуществляет обработку сообщений от пользователя в чате телеграм бота
     *
     * @param message объект содержащий текст сообщения от пользователя в чат боте
     * @return код ошибки обработки сообщения. 0 - означает отсутствие ошибок.
     */
    public String processMessage(Message message) {
        Long userId = message.from().id();
        Long chatId = message.chat().id();
        String massage = "/info_dog_shelter";
//        String textMassage = message.text();
//        PhotoSize[] photoSizes = message.photo();

            return massage;

        }
        /**
         * метод осуществляет обработку нажатий кнопок меню
         *
         * @param callbackQuery объект содержащий текст ответа пользователя а также исходное сообщение
         * @return код ошибки обработки сообщения. 0 - означает отсутствие ошибок.
         */
        public String processCallBackQuery (CallbackQuery callbackQuery) {
            Long chatId = callbackQuery.message().chat().id();
            Long userId = callbackQuery.from().id();
            String callbackCommand = callbackQuery.data();
            return processCommand(userId, chatId, callbackCommand);
        }

        private String processCommand (Long userId, Long chatId, String commandStr){
            if (Commands.CAT_SHELTER.getCommand().equals(commandStr)) {
                return sendCatShelterMenu(chatId);
            } else if (Commands.DOG_SHELTER.getCommand().equals(commandStr)) {
                return sendDogShelterMenu(chatId);
            }
            return "";
        }

        private String sendDogShelterMenu (Long chatId){
            return "информация по приюту для собак";
        }

        private String sendCatShelterMenu (Long chatId){
            return "информация по приюту для кошек";
        }
    public SendResponse sendReply(Long chatId, String text, Keyboard keyboard) {
        SendMessage message = new SendMessage(chatId, text);
        message.parseMode(ParseMode.Markdown);
        message.replyMarkup(keyboard);
        return telegramBot.execute(message);
    }
//        public SendResponse sendReply (Long chatId, String text){
//        public String sendReply (Long chatId, String text){
//        public String sendReply (String message11){
////            SendMessage message = new SendMessage(chatId, text);
//            message11 = "info33";
//            //message.parseMode(ParseMode.MarkdownV2);
////            return "info33";
//           return sendReply(message11);
//        }
    }

