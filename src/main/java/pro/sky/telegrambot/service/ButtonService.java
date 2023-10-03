package pro.sky.telegrambot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Photo;
import pro.sky.telegrambot.repository.R_Animal;
import pro.sky.telegrambot.repository.R_Photo;
import pro.sky.telegrambot.repository.R_Shelter;
import pro.sky.telegrambot.standard.Commands;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ButtonService {

    private  long chatId;
    private  String text;

    private final R_Photo photoRepository;
    private final R_Animal animalRepository;
    private final R_Shelter shelterRepository;

    public ButtonService(R_Photo photoRepository, R_Animal animalRepository, R_Shelter shelterRepository) {
        this.photoRepository = photoRepository;
        this.animalRepository = animalRepository;
        this.shelterRepository = shelterRepository;
    }

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


    public SendMessage setButtonStartMenu(long chatId, String text) {
        SendMessage message = new SendMessage();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        //List<InlineKeyboardButton> rowInline2 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(Commands.DOG_SHELTER.getDescription());
        inlineKeyboardButton1.setCallbackData(Commands.DOG_SHELTER.getCommand());

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText(Commands.CAT_SHELTER.getDescription());
        inlineKeyboardButton2.setCallbackData(Commands.CAT_SHELTER.getCommand());

        rowInline1.add(inlineKeyboardButton1);
        rowInline1.add(inlineKeyboardButton2);

        message.setChatId(chatId);
        message.setText(text);

        rowsInline.add(rowInline1);
        //rowsInline.add(rowInline2);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        return message;
    }

    public SendMessage setButtonDogShelterInfo(long chatId, String text) {
        SendMessage message = new SendMessage();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(Commands.DOG_SHELTER_CONTACT_INFO.getDescription());
        inlineKeyboardButton1.setCallbackData(Commands.DOG_SHELTER_CONTACT_INFO.getCommand());

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText(Commands.DOG_SHELTER_DATA_SECURITY_PASS.getDescription());
        inlineKeyboardButton2.setCallbackData(Commands.DOG_SHELTER_DATA_SECURITY_PASS.getCommand());

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText(Commands.DOG_SHELTER_SAFETY_RECOMMENDATIONS.getDescription());
        inlineKeyboardButton3.setCallbackData(Commands.DOG_SHELTER_SAFETY_RECOMMENDATIONS.getCommand());

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText(Commands.DOCUMENTS_TAKE_ANIMAL.getDescription());
        inlineKeyboardButton4.setCallbackData(Commands.DOCUMENTS_TAKE_ANIMAL.getCommand());


        rowInline1.add(inlineKeyboardButton1);
        rowInline2.add(inlineKeyboardButton2);
        rowInline3.add(inlineKeyboardButton3);
        rowInline4.add(inlineKeyboardButton4);

        message.setChatId(chatId);
        message.setText(text);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }


    public List<Animal> countCat(){
        return animalRepository.findAnimalByAnimalTypeIgnoreCase("кот");
    }

    public Photo findPhoto(long id){
        return photoRepository.findPhotoByAnimalId(id);
    }
    public SendMessage setCatMenu(long chatId, String text){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }
}