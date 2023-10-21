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
}
