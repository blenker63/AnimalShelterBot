package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.*;
import pro.sky.telegrambot.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AddService {
    private final R_Animal animalRepository;
    private final R_AnimalOwner animalOwnerRepository;
    private final R_PetReport petReportRepository;
    private final R_Shelter shelterRepository;
    // private final R_User userRepository;
    private final R_Volunteer volunteerRepository;

    public AddService(R_Animal animalRepository,
                      R_AnimalOwner animalOwnerRepository,
                      R_PetReport petReportRepository,
                      R_Shelter shelterRepository,
                      R_Volunteer volunteerRepository) {
        this.animalRepository = animalRepository;
        this.animalOwnerRepository = animalOwnerRepository;
        this.petReportRepository = petReportRepository;
        this.shelterRepository = shelterRepository;
        //this.userRepository = userRepository;
        this.volunteerRepository = volunteerRepository;
    }
//    private AnimalOwner animalOwner;
//      public AddService(AnimalOwner animalOwner) {
//        this.animalOwner = animalOwner;
//      }

    public void anSet(long s_id, long id) {
        animalRepository.AnimalSet(s_id, id);
    }


    /**
     * Сохраняет животных в БД Animal
     *
     * @param animal сущность животное кот/собака

     */
    public Animal AnimalSave(Animal animal) {
        return animalRepository.save(animal);
    }

    /**
     * Сохраняет в БД AnimalOwner данные Владелеца животного
     *
     * @param animalOwner сущность владелец животного
     */
    public AnimalOwner AnimalOwnerSave(AnimalOwner animalOwner) {
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

//    /**
//     *  Сохранение отчета о домашних животных
//     * @param diet  диета
//     * @param feelings  состояние животного
//     * @param control  проверялось ли благоустройство животного
//     */
//    public PetReport PetReportSave(long id, String diet, String feelings, boolean control){
//        LocalDate date = LocalDate.now();
//        PetReport petReport = new PetReport(id, diet, feelings, control, date);
//        return petReportRepository.save(petReport);
//    }

    /**
     * Метод для сохранения данных о приюте
     *
     * @param shelter сущность приют для кошек/для собак
     */
    public Shelter ShelterSave(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

//    public User UserSave(String name, String phoneNumber, Long chatId, LocalDateTime localDateTime){
//        User user = new User(name, phoneNumber,chatId, localDateTime);
//        return userRepository.save(user);
//    }

    public Volunteer VolunteerSave(String name, String phoneNumber) {
        Volunteer volunteer = new Volunteer(name, phoneNumber);
        return volunteerRepository.save(volunteer);
    }


}
