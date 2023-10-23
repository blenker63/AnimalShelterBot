package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.PhotoReport;

import java.time.LocalDate;

public interface R_PhotoReport extends JpaRepository<PhotoReport,Integer> {

    PhotoReport findPhotoReportByOwnerIdAndDate(Long owner_id, LocalDate date);
}
