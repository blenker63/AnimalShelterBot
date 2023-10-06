package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.*;
import pro.sky.telegrambot.repository.*;

import java.time.LocalDateTime;

@Service
public class AddService {
    private final R_Animal animalRepository;
    private final R_AnimalOwner animalOwnerRepository;
    private final R_Information informationRepository;
    private final R_PetReport petReportRepository;
    private final R_Shelter shelterRepository;
    private final R_User userRepository;
    private final R_Volunteer volunteerRepository;

    public AddService(R_Animal animalRepository,
                      R_AnimalOwner animalOwnerRepository,
                      R_Information informationRepository,
                      R_PetReport petReportRepository,
                      R_Shelter shelterRepository,
                      R_User userRepository,
                      R_Volunteer volunteerRepository) {
        this.animalRepository = animalRepository;
        this.animalOwnerRepository = animalOwnerRepository;
        this.informationRepository = informationRepository;
        this.petReportRepository = petReportRepository;
        this.shelterRepository = shelterRepository;
        this.userRepository = userRepository;
        this.volunteerRepository = volunteerRepository;
    }

    public void anSet(long s_id, long id) {
        animalRepository.AnimalSet(s_id,id);
    }




    /**
     *  Сохраняет животных в БД Animal
     * @param animalType  тип животного кот/собака
     * @param name  имя животного
     * @param age  возраст животного
     * @param breed  порода
     */
    public Animal AnimalSave(String animalType, String name, int age, String breed){
        Animal animal = new Animal(animalType, name, age, breed);
       return animalRepository.save(animal);
    }

    /**
     *  Сохраняет в БД AnimalOwner данные Владелеца животного
     * @param name  имя
     * @param phoneNumber номер телефона
     * @param eMail  электронная почта
     * @param trialPeriod  испытательный срок
     */
    public AnimalOwner AnimalOwnerSave(String name, String phoneNumber,  String eMail, boolean trialPeriod){
        AnimalOwner animalOwner = new AnimalOwner(name, phoneNumber, eMail, trialPeriod);
       return animalOwnerRepository.save(animalOwner);
    }

//    /**
//     *  Метод для сохранения данных пользователя написавшего в чат,
//     * @param chatId - ID пользователя
//     * @param name - имя
//     *             дата и время принятого сообщения
//     */
//    public void BotUserSave(Long chatId, String name){
//        LocalDateTime localDateTime = LocalDateTime.now();
//        BotUser botUser = new BotUser(chatId, name, localDateTime);
//        botUserRepository.save(botUser);
//    }

    /**
     *
     * @param info  информация
     */
    public Information InformationSave(String info){
        Information information = new Information();
                    information.setInfo(info);
        return informationRepository.save(information);
    }

    /**
     *  Сохранение отчета о домашних животных
     * @param diet  диета
     * @param feelings  состояние животного
     * @param check  проверялось ли благоустройство животного
     */
    public PetReport PetReportSave(String diet, String feelings, boolean check){
        LocalDateTime localDateTime = LocalDateTime.now();
        PetReport petReport = new PetReport(diet, feelings, check, localDateTime);
        return petReportRepository.save(petReport);
    }

    /**
     *  Метод для сохранения данных о приюте
     * @param shelterType  тип приюта для кошек/для собак
     * @param shelterName  названия приюта
     * @param address  адрес местонахождения приюта
     */
    public Shelter ShelterSave(String shelterType, String shelterName, String address, String information){
        Shelter shelter = new Shelter(shelterType, shelterName, address, information);
       return shelterRepository.save(shelter);
    }

    public User UserSave(String name, String phoneNumber, Long chatId, LocalDateTime localDateTime){
        User user = new User(name, phoneNumber,chatId, localDateTime);
        return userRepository.save(user);
    }

    public Volunteer VolunteerSave(String name, String phoneNumber){
        Volunteer volunteer = new Volunteer(name, phoneNumber);
        return volunteerRepository.save(volunteer);
    }
}
