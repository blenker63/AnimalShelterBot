package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
//import jakarta.persistence.*;

@Entity
@Table(name = "shelter")
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "shelter_type")
    private String shelterType;
    @Column(name = "shelter_name")
    private String shelterName;
    @Column(name = "address")
    private String address;
    @Column(name = "information")
    private String information;

    @OneToMany(mappedBy = "shelter")
    private Collection<Animal> animals;

    public Shelter(String shelterType, String shelterName, String address, String information) {
        this.shelterType = shelterType;
        this.shelterName = shelterName;
        this.address = address;
        this.information = information;
    }

    public Shelter() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShelterType() {
        return shelterType;
    }

    public void setShelterType(String shelterType) {
        this.shelterType = shelterType;
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", shelterType='" + shelterType + '\'' +
                ", shelterName='" + shelterName + '\'' +
                ", address='" + address + '\'' +
                ", infomation='" + information + '\'' +
                '}';
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shelter)) return false;
        Shelter shelter = (Shelter) o;
        return id == shelter.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
