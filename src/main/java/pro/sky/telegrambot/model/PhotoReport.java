package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
//import jakarta.persistence.*;

@Entity
@Table(name = "photo_report")
public class PhotoReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ownerId")
    private long ownerId;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "path")
    private String path;

    public PhotoReport(long ownerId, LocalDate date, String path) {
        this.ownerId = ownerId;
        this.date = date;
        this.path = path;
    }

    public PhotoReport() {
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoReport that = (PhotoReport) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
