package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Photo;

import java.util.List;

public interface R_Animal extends JpaRepository<Animal,Long> {

    /**
     * прикрепляем животного к приюту
     * @param shelter_id приют
     * @param animal_id животного
     */
    @Query(value = "UPDATE public.animal SET shelter_id = :shelter_id WHERE animal_id = :animal_id", nativeQuery = true)
    void AnimalSetShelter(long shelter_id, long animal_id);

    /**
     * поиск животного по типу
     * @param type тип животного
     * @return список животных
     */
    List<Animal> findAnimalByAnimalTypeIgnoreCase(String type);

    /**
     * поиск животного по id
     * @param id животного
     * @return инфо о животном
     */
    Animal findAnimalById(Long id);

    /**
     * поиск типа животного по id
     * @param id животного
     * @return тип животного
     */
    @Query(value = "SELECT animal_type FROM animal WHERE  id = :id", nativeQuery = true)
    String findAnimalTypeById(Long id);



}
