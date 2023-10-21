package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.PhotoReport;

public interface R_PhotoReport extends JpaRepository<PhotoReport,Integer> {
}
