package pro.sky.telegrambot.model;

//import jakarta.persistence.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "animal_owner")
public class AnimalOwner {
    @Id
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "e_mail")
    private String email;
    @Column(name = "trial_period")
    private boolean trialPeriod;
//    @OneToOne(mappedBy = "animaOwner")
//    private PetReport petReport;


    @Column(name = "date")
    private LocalDate date;

    public AnimalOwner(String name, String phoneNumber, String email, boolean trialPeriod, LocalDate date) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.trialPeriod = trialPeriod;
        this.date = date;
    }

    public AnimalOwner() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTrialPeriod() {
        return trialPeriod;
    }

    public void setTrialPeriod(boolean trialPeriod) {
        this.trialPeriod = trialPeriod;
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
        if (!(o instanceof AnimalOwner)) return false;
        AnimalOwner that = (AnimalOwner) o;
        return id == that.id && trialPeriod == that.trialPeriod && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, trialPeriod, date);
    }

    @Override
    public String toString() {
        return "AnimalOwner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
