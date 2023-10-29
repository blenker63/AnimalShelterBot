package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.PetReport;

import java.time.LocalDate;

public interface R_PetReport extends JpaRepository<PetReport,Integer> {

    PetReport findPetReportByOwnerIdAndDate(Long ownerId, LocalDate date);

    @Query(value = "UPDATE public.pet_report set diet =:diet WHERE id =:id ", nativeQuery = true)
    void saveDiet(long id, String diet);

    @Query(value = "UPDATE public.pet_report set feelings =:feelings WHERE id =:id ", nativeQuery = true)
    void saveFeelings(long id, String feelings);

    @Query(value = "SELECT * FROM pet_report WHERE owner_id =:owner_id AND date = (select max(date) from pet_report where owner_id =:owner_id)", nativeQuery = true)
    PetReport checkingLastDateReports(long owner_id);

//    public interface R_PetReport extends JpaRepository<PetReport, Long> {
////    List<PetReport> findAll();
//    }
}
