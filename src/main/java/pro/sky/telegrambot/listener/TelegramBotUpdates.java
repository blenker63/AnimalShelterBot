package pro.sky.telegrambot.listener;

import lombok.Data;
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
import pro.sky.telegrambot.service.ButtonService;
import pro.sky.telegrambot.standard.Commands;

import java.util.List;


@Component
@Slf4j
public class TelegramBotUpdates extends TelegramLongPollingBot {

    private final ButtonService buttonService;


    public TelegramBotUpdates(@Value("${telegram.bot.token}") String botToken, ButtonService buttonService) {
        super(botToken);
        this.buttonService = buttonService;
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
                //sendMessage(id, "Вас приветствует приют КотоПес!");
                try {
                    execute(buttonService.setButtonStartMenu(id, "Привет " + userName + ", я помогу тебе подобрать домашнего питомца." +
                            " Предлагаю посмотреть информацию о приютах. " ));
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }
        }
        // Если пришло нажатие кнопки
        if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            String chat_id = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            
            if (call_data.equals(Commands.DOG_SHELTER.getCommand())) {
                try {
                    execute(new SendMessage(chat_id, "Переход на песиков успешно"));
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }

            if (call_data.equals(Commands.CAT_SHELTER.getCommand())) {
                List<Animal> cat = buttonService.countCat();
                sendMessage(chatId, cat.get(0).getName());
                //var photo = new com.pengrad.telegrambot.request.SendPhoto(chatId, "C:\\cat1.jpg");
                //var photo = new SendPhoto(String.valueOf(chatId), new InputFile("C:\\cat1.jpg"));
                var photo = new SendPhoto();
                photo.setChatId(chatId);
                photo.setPhoto(new InputFile( "C:\\cat1.jpg"));
                try {
                    execute(photo);
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }
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


}


