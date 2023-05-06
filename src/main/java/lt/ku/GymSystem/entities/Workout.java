package lt.ku.GymSystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Pavadinimas yra privalomas laukelis.")
    @Length(min = 3, max = 20, message = "Pavadinimas turi būti nuo 3 iki 20 simbolių.")
    @Column
    private String name;

    @NotEmpty(message = "Data yra privaloma laukelis.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column
    private String date;

    @NotNull(message = "Vietos yra privaloma laukelis.")
    @Column
    private Integer places;

    @NotEmpty(message = "Vieta yra privalomas laukelis.")
    @Length(min = 3, max = 20, message = "Vieta turi būti nuo 3 iki 20 simbolių.")
    @Column
    private String location;

    public Object getAttribute(String name) {
        return switch (name.toLowerCase()) {
            case "id" -> getId();
            case "name" -> getName();
            case "date" -> getDate();
            case "places" -> getPlaces();
            case "location" -> getLocation();
            default -> "";
        };
    }

    @Override
    public String toString() {
        return name + ", " + date;
    }

    public Workout() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}