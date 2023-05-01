package lt.ku.GymSystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name="workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String date;

    @Column
    private Integer places;

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

    public Workout(String name, String date, Integer places, String location) {
        this.name = name;
        this.date = date;
        this.places = places;
        this.location = location;
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