package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.PetReport;

import java.time.LocalDateTime;
import java.util.Collection;

public interface R_PetReport extends JpaRepository<PetReport,Long> {
//    List<PetReport> findAll();

    Collection<PetReport> findById(long id);

//        Collection<PetReport> findPetReportByDate(LocalDateTime date);
//    Collection<PetReport> findAllByDate(Date date);
//    Collection<PetReport> findByDate(Date date);
    Collection<PetReport> findByDateBetween(LocalDateTime begin, LocalDateTime end);
    Collection<PetReport> findByCheck(boolean check);

}
