package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.AnimalOwner;

import java.util.List;

public interface R_AnimalOwner extends JpaRepository<AnimalOwner,Long> {

    /**
     * поиск владельца животного по id
     * @param id владельца
     * @return инфо о владельце
     */
    AnimalOwner findAnimalOwnerById(long id);
    List<AnimalOwner> findAll();


    @Query(value = "UPDATE public.animal_owner set trial_period = false WHERE id =:id ", nativeQuery = true)
    void trialPeriodOff(long id);
}
