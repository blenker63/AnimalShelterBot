package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.standard.Commands;

import java.util.List;

@Service
public class KeyboardService {

    /**
     * метод создает меню бота на основе входящего списка стандартных команд
     *
     * @param commandsList список стандартных команд класса Commands на основе которых формируется меню бота
     * @return объект - меню бота для user
     */
    private Keyboard сreatingKeyboard(List<Commands> commandsList) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        for (Commands command : commandsList) {
            InlineKeyboardButton inlineButton = new InlineKeyboardButton(command.getDescription());
            inlineButton.callbackData(command.getCommand());
            keyboard.addRow(inlineButton);
        }
        return keyboard;
    }

    /**
     * метод создает меню выбора приюта
     *
     * @return объект - меню бота для user
     */
    public Keyboard shelterSelectionMenu() {
        List<Commands> commandsList = List.of(Commands.CAT_SHELTER, Commands.DOG_SHELTER);
        return сreatingKeyboard(commandsList);
    }
}
