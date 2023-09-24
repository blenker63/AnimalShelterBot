package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Information;

public interface R_Information extends JpaRepository<Information,Long> {
}
