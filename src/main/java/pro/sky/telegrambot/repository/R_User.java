package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.User;

public interface R_User extends JpaRepository<User,Long> {
    User findUserByChatId(long id);
}
