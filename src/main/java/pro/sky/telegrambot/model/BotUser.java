package pro.sky.telegrambot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import pro.sky.telegrambot.repository.R_BotUser;

//import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class BotUser {
    @Id
    private Long userId;
    private String name;
    private LocalDateTime localDateTime;

    public BotUser(Long userId, String name, LocalDateTime localDateTime) {
        this.userId = userId;
        this.name = name;
        this.localDateTime = localDateTime;
    }

    public BotUser() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotUser botUser = (BotUser) o;
        return Objects.equals(userId, botUser.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
