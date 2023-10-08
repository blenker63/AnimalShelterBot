package pro.sky.telegrambot.service;

import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.BotUser;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repository.R_BotUser;
import pro.sky.telegrambot.repository.R_User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class UserService {

    private final R_BotUser botUserRepository;
    private final R_User userRepository;

    public UserService(R_BotUser botUserRepository, R_User userRepository) {
        this.botUserRepository = botUserRepository;
        this.userRepository = userRepository;
    }

    /***************************************************************
     *   Сохраняем в БД нового пользователя
     * @param id  id пользователя
     * @param name имя пользователя
     ***************************************************************/
    public void saveBotUser(long id, String name){
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        BotUser user = new BotUser(id, name, date);
        botUserRepository.save(user);
    }

    /*********************************************************************
     *   Сохраняем пользователя сделавшего заявку
     * @param id id пользователя
     * @param animalId id животного
     *********************************************************************/
    public void saveUser(long id, long animalId){
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        BotUser botUser =  botUserRepository.findBotUserByUserId(id);
        User user = new User(id, botUser.getName(), date, animalId);
        userRepository.save(user);
    }

    public BotUser findUserById(long id){
        BotUser botUser = botUserRepository.findBotUserByUserId(id);
        return botUser;
    }

    public User findUserAndAnimal(long id){
        User user = userRepository.findUserByChatId(id);
        return user;
    }
}
