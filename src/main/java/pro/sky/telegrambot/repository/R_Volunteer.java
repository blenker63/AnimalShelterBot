package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Volunteer;

public interface R_Volunteer extends JpaRepository<Volunteer,Long> {
}
