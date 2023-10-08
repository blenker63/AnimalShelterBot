package pro.sky.telegrambot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pro.sky.telegrambot.model.Photo;

public interface R_Photo extends PagingAndSortingRepository<Photo,Long> {

    Photo findPhotoByAnimalId(Long Id);

    //void save(Photo photo);


    //Object save(Photo photo);
}
