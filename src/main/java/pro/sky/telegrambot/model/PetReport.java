package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pet_report")
public class PetReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "diet")
    private String diet;
    @Column(name = "feelings")
    private String feelings;

    @Column(name = "check")
    private boolean check;

    public PetReport(String diet, String feelings, boolean check) {
        this.diet = diet;
        this.feelings = feelings;
        this.check = check;
    }

    public PetReport() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetReport)) return false;
        PetReport petReport = (PetReport) o;
        return id == petReport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PetReport{" +
                "id=" + id +
                ", diet='" + diet + '\'' +
                ", feelings='" + feelings + '\'' +
                ", check=" + check +
                '}';
    }
}