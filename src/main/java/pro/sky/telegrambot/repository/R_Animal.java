package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Animal;

public interface R_Animal extends JpaRepository<Animal,Long> {
}
