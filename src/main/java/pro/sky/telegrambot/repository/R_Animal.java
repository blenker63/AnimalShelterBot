package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Photo;

import java.util.List;

public interface R_Animal extends JpaRepository<Animal,Long> {

    @Query(value = "UPDATE public.animal SET shelter_id = :s_id WHERE id = :id", nativeQuery = true)
    void AnimalSet(long s_id, long id);

    List<Animal> findAnimalByAnimalTypeIgnoreCase(String type);

    Animal findAnimalById(Long id);

    @Query(value = "SELECT animal_type FROM animal WHERE  id = :id", nativeQuery = true)
    String findAnimalTypeById(Long id);



}
