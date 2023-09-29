package pro.sky.telegrambot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.telegrambot.service.ButtonService;
import pro.sky.telegrambot.standard.Commands;

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userName = update.getMessage().getChat().getFirstName();
            String message = update.getMessage().getText();
            Long id = update.getMessage().getChatId();

            if (message.equals(Commands.START.getCommand())) {
                log.info(userName);
                //StartCom(id, userName);
                try {
                    execute(buttonService.setButton(id, "Вас приветствует приют КотоПес!"));
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }
        }
        if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            String chat_id = String.valueOf(update.getCallbackQuery().getMessage().getChatId());

            if (call_data.equals(Commands.DOG_SHELTER.getCommand())) {
                try {
                    execute(new SendMessage(chat_id, "Переход прошел успешно"));
                } catch (TelegramApiException e) {
                    log.error("Сообщение не отправлено!");
                }
            }
        }
    }
}


