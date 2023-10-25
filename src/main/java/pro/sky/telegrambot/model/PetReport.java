package pro.sky.telegrambot.model;
import jakarta.persistence.*;
//import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pet_report")
public class PetReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "owner_Id")
    private Long ownerId;
    @Column(name = "diet")
    private String diet;
    @Column(name = "feelings")
    private String feelings;
    @Column(name = "control")
    private boolean control;
    @Column(name = "date")
    private LocalDate date;

    public PetReport(long ownerId, String diet, String feelings, boolean control, LocalDate date) {
        this.ownerId = ownerId;
        this.diet = diet;
        this.feelings = feelings;
        this.control = control;
        this.date = date;
    }

    public PetReport() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getFeelings() {
        return feelings;
    }

    public void setFeelings(String feelings) {
        this.feelings = feelings;
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetReport petReport = (PetReport) o;
        return id == petReport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
