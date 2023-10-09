package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.User;

public interface R_User extends JpaRepository<User,Long> {
    User findUserByChatId(long id);

    @Query(value = "UPDATE public.users set telephone =:phone WHERE chat_id =:id ", nativeQuery = true)
    void savePhone(long id, String phone);
}
