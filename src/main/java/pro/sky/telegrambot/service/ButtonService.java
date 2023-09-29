package pro.sky.telegrambot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import pro.sky.telegrambot.standard.Commands;

import java.util.ArrayList;
import java.util.List;

@Component
public class ButtonService {
    private  long chatId;
    private  String text;

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SendMessage setButton(long chatId, String text) {
        SendMessage message = new SendMessage();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText(Commands.DOG_SHELTER.getDescription());
        inlineKeyboardButton1.setCallbackData(Commands.DOG_SHELTER.getCommand());
        rowInline1.add(inlineKeyboardButton1);

        message.setChatId(chatId);
        message.setText(text);

        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        return message;
    }
}
