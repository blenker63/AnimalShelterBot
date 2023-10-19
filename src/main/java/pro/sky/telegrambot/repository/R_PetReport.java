package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.PetReport;

public interface R_PetReport extends JpaRepository<PetReport,Long> {
//    List<PetReport> findAll();
}
