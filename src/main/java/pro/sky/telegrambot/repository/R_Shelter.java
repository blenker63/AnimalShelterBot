package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Shelter;

public interface R_Shelter extends JpaRepository<Shelter,Long> {
}
