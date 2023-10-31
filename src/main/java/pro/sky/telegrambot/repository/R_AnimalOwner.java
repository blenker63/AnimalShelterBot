package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.AnimalOwner;

import java.util.List;

public interface R_AnimalOwner extends JpaRepository<AnimalOwner,Long> {
    AnimalOwner findAnimalOwnerById(long id);
    List<AnimalOwner> findAll();
}
