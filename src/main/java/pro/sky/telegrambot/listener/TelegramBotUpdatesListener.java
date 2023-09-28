package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.service.BotService;
import pro.sky.telegrambot.service.KeyboardService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final BotService botService;


//    @Autowired
//    private TelegramBot telegramBot;
    @Autowired
    KeyboardService keyboardService;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, BotService botService) {
        this.telegramBot = telegramBot;
        this.botService = botService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            CallbackQuery callbackQuery = update.callbackQuery();
            String processMassageCode = "0";
            String logMessage = "";
            // Process your updates here
            if (update.message().text().equals("/start")) {
                SendMessage text = new SendMessage(update.message().chat().id(),
                        "Привет " + update.message().from().firstName() + "! Вас приветствует приют для животных КотоПёс!");
                SendResponse sendResponse = telegramBot.execute(text);
                SendMessage message1 = new SendMessage(update.message().chat().id(), "выберите приют который вам необходим")
                        .replyMarkup(keyboardService.shelterSelectionMenu());
                SendResponse sendResponse1 = telegramBot.execute(message1);
                System.out.println(update.message().text());
            } else if (update.message().text().equals("/info_dog_shelter")) {
//            } else if (callbackQuery != null) {
//            } else if (callbackQuery != null) {
////                if (callbackQuery != null && !callbackQuery.from().isBot()) {
////                int processMassageCode = botService.processCallBackQuery(callbackQuery);
//
                    SendMessage message2 = new SendMessage(update.message().chat().id(), "выберите необходимое действие")
                            .replyMarkup(keyboardService.generateDogShelterMenu());
                    SendResponse sendResponse2 = telegramBot.execute(message2);

//
                }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}

