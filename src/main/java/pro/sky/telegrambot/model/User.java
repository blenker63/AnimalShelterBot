package pro.sky.telegrambot.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
//import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long chatId;
    private String name;
    private LocalDateTime localDateTime;
    private Long animalId;

    private String telephone;

    public User(Long chatId, String name, LocalDateTime localDateTime, Long animalId) {
        this.chatId = chatId;
        this.name = name;
        this.localDateTime = localDateTime;
        this.animalId = animalId;
    }

//    public User(Long chatId, String name, LocalDateTime localDateTime, Long animalId) {
//        this.chatId = chatId;
//        this.name = name;
//        this.localDateTime = localDateTime;
//        this.animalId = animalId;
//    }

    public User() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(chatId, user.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }
}
