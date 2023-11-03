package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.PhotoReport;

import java.time.LocalDate;

public interface R_PhotoReport extends JpaRepository<PhotoReport,Integer> {

    PhotoReport findPhotoReportByOwnerIdAndDate(Long owner_id, LocalDate date);

    @Query(value = "UPDATE public.photo_report set path =:dir WHERE owner_id =:id ", nativeQuery = true)
    PhotoReport recordDirPhoto(long id, String dir);
}


