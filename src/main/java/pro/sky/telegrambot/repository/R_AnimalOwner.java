package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
