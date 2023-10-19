package pro.sky.telegrambot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.model.Animal;
import pro.sky.telegrambot.model.Photo;
import pro.sky.telegrambot.repository.R_Animal;
import pro.sky.telegrambot.repository.R_Photo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@Service
public class PhotoService {

    private final R_Photo rPhoto;
    private final R_Animal rAnimal;

    public PhotoService(R_Photo rPhoto, R_Animal rAnimal) {
        this.rPhoto = rPhoto;
        this.rAnimal = rAnimal;
    }

    @Value("photos")
    private String photosDir;

    public void uploadPhoto(Long animalId, MultipartFile photoFile) throws IOException {
        log.info( "uploadAvatar = OK!");
        Animal animal = rAnimal.getById(animalId);
        Path filePath = Path.of(photosDir, animal + "." + getExtensions(Objects.requireNonNull(photoFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = photoFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        //Avatar avatar = findAvatar(studentId);
        Photo photo = new Photo();
        photo.setAnimal(animal);
        photo.setFilePath(filePath.toString());
        photo.setFileSize(photoFile.getSize());
        photo.setMediaType(photoFile.getContentType());
        photo.setData(photoFile.getBytes());
        rPhoto.save(photo);
    }
    private String getExtensions(String fileName) {

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Photo findPhoto(long animalId){
//        log.info( "findAvatar = OK!");
        return rPhoto.findPhotoByAnimalId(animalId);
    }

    public List<Photo> avatarPage(Integer page){
        log.info( "avatarPage = OK!");
        return rPhoto.findAll(PageRequest.of(page - 1, 3)).getContent();
    }

}
