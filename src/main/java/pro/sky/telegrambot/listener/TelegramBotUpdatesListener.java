package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.service.KeyboardService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    KeyboardService keyboardService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            if (update.message().text().equals("/start")) {
                SendMessage text = new SendMessage(update.message().chat().id(),
                        "Привет " + update.message().from().firstName() + "! Вас приветствует приют для животных КотоПёс!");
                SendResponse sendResponse = telegramBot.execute(text);
                SendMessage message = new SendMessage(update.message().chat().id(), "выберите приют который вам необходим")
                        .replyMarkup(keyboardService.shelterSelectionMenu());
                SendResponse sendResponse1 = telegramBot.execute(message);





            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
