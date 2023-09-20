package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "animal_type")
    private String animalType;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public Animal(String animalType, String name, int age) {
        this.animalType = animalType;
        this.name = name;
        this.age = age;
    }

    public Animal() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", animalType='" + animalType + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
