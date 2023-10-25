package pro.sky.telegrambot.model;

import jakarta.persistence.*;

//import javax.persistence.*;
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
    private String eMail;
    @Column(name = "trial_period")
    private boolean trialPeriod;
//    @OneToOne(mappedBy = "animaOwner")
//    private PetReport petReport;


    @Column(name = "date")
    private LocalDate date;

    public AnimalOwner(String name, String phoneNumber, String eMail, boolean trialPeriod, LocalDate date) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimalOwner)) return false;
        AnimalOwner that = (AnimalOwner) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AnimalOwner{" + ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", e_mail='" + eMail + '\'' +
                ", trialPeriod=" + trialPeriod +
                '}';
    }
}
