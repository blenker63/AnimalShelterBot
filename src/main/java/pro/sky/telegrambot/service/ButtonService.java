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
        List<InlineKeyboardButton> rowInline5 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(Commands.DOG_SHELTER_CONTACT_INFO.getDescription());
        inlineKeyboardButton1.setCallbackData(Commands.DOG_SHELTER_CONTACT_INFO.getCommand());

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText(Commands.DOG_SHELTER_DATA_SECURITY_PASS.getDescription());
        inlineKeyboardButton2.setCallbackData(Commands.DOG_SHELTER_DATA_SECURITY_PASS.getCommand());

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText(Commands.DOG_SHELTER_SAFETY_RECOMMENDATIONS.getDescription());
        inlineKeyboardButton3.setCallbackData(Commands.DOG_SHELTER_SAFETY_RECOMMENDATIONS.getCommand());

//        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
//        inlineKeyboardButton4.setText("Ознакомится с условиями");
//        inlineKeyboardButton4.setCallbackData("/information_for_adopting_an_dog");

        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        inlineKeyboardButton5.setText(Commands.SHELTER_DOG_ANIMAL.getDescription());
        inlineKeyboardButton5.setCallbackData(Commands.SHELTER_DOG_ANIMAL.getCommand());


        rowInline1.add(inlineKeyboardButton1);
        rowInline2.add(inlineKeyboardButton2);
        rowInline3.add(inlineKeyboardButton3);
//        rowInline4.add(inlineKeyboardButton4);
        rowInline5.add(inlineKeyboardButton5);

        message.setChatId(chatId);
        message.setText(text);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);
        rowsInline.add(rowInline5);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }

    public SendMessage setButtonCatShelterInfo(long chatId, String text) {
        SendMessage message = new SendMessage();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline5 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(Commands.CAT_SHELTER_CONTACT_INFO.getDescription());
        inlineKeyboardButton1.setCallbackData(Commands.CAT_SHELTER_CONTACT_INFO.getCommand());

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText(Commands.CAT_SHELTER_DATA_SECURITY_PASS.getDescription());
        inlineKeyboardButton2.setCallbackData(Commands.DOG_SHELTER_DATA_SECURITY_PASS.getCommand());

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText(Commands.CAT_SHELTER_SAFETY_RECOMMENDATIONS.getDescription());
        inlineKeyboardButton3.setCallbackData(Commands.CAT_SHELTER_SAFETY_RECOMMENDATIONS.getCommand());

//        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
//        inlineKeyboardButton4.setText("Ознакомится с условиями");
//        inlineKeyboardButton4.setCallbackData("/information_for_adopting_an_cat");

        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        inlineKeyboardButton5.setText(Commands.SHELTER_CAT_ANIMAL.getDescription());
        inlineKeyboardButton5.setCallbackData(Commands.SHELTER_CAT_ANIMAL.getCommand());


        rowInline1.add(inlineKeyboardButton1);
        rowInline2.add(inlineKeyboardButton2);
        rowInline3.add(inlineKeyboardButton3);
//        rowInline4.add(inlineKeyboardButton4);
        rowInline5.add(inlineKeyboardButton5);

        message.setChatId(chatId);
        message.setText(text);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);
        rowsInline.add(rowInline5);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }

    public SendMessage setButtonLooKAnimal(long chatId, String animalId, String text) {
        SendMessage message = new SendMessage();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Подробнее");
        inlineKeyboardButton1.setCallbackData(animalId);

        rowInline1.add(inlineKeyboardButton1);

        message.setChatId(chatId);
        message.setText("\t"+text);

        rowsInline.add(rowInline1);
        //rowsInline.add(rowInline2);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        return message;
    }

    /************************************************************
     * Кнопка обработки "Подать заявку"
     * @param chatId пользователя
     * @param animalId животного
     * @param text текст
     * @return сообщение
     ************************************************************/
    public SendMessage choiceAnimal(long chatId, String animalId, String text) {
        SendMessage message = new SendMessage();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Подать  заявку.");
        inlineKeyboardButton1.setCallbackData("ID"+animalId);
        rowInline1.add(inlineKeyboardButton1);
        message.setChatId(chatId);
        message.setText("    "+text);
        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        return message;
    }

    /***********************************************************************
     * Кнопка по обработке вывода информации по условиям для взятия животного
     * @param chatId пользователя
     * @param animalType тип животного для выбора возвращаемого калбека
     * @param text текст
     * @return сообщение
     *********************************************************************/
    public SendMessage informationForAdoptingAnAnimal(long chatId, String animalType, String text) {
        SendMessage message = new SendMessage();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Ознакомится с условиями");
        if (animalType.equals("кот")){inlineKeyboardButton1.setCallbackData("/information_for_adopting_an_cat");}
        if (animalType.equals("собака")){inlineKeyboardButton1.setCallbackData("/information_for_adopting_an_dog");}
        rowInline1.add(inlineKeyboardButton1);

        message.setChatId(chatId);
        message.setText(text);

        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        return message;
    }

    public SendMessage sendReport(long chatId, String text){
        SendMessage message = new SendMessage();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Отправить отчет.");
        inlineKeyboardButton1.setCallbackData("/send_report");
        rowInline1.add(inlineKeyboardButton1);

        message.setChatId(chatId);
        message.setText(text);

        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        return message;
    }


    public List<Animal> countCat(){
        return animalRepository.findAnimalByAnimalTypeIgnoreCase("кот");
    }

    public List<Animal> countDog(){
        return animalRepository.findAnimalByAnimalTypeIgnoreCase("собака");
    }

    public Animal findCallBackByAnimal(Long date){
        return animalRepository.findAnimalById(date);
    }

    public String findAnimalTypeById(long id){
        return animalRepository.findAnimalTypeById(id);
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