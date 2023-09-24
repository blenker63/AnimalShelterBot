package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.AnimalOwner;

public interface R_AnimalOwner extends JpaRepository<AnimalOwner,Long> {
}
