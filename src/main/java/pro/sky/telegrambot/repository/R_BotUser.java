package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.BotUser;

public interface R_BotUser extends JpaRepository<BotUser,Long> {

    BotUser findBotUserByUserId(long id);
}
