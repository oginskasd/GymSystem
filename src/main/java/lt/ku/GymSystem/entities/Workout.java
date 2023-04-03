package lt.ku.GymSystem.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64)
    private String name;

    @Column()
    private Date date;

    @Column()
    private Integer places;

    @Column(length = 256)
    private String location;

    public Workout() {
    }

    public Workout(String name, Date date, Integer places, String location) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", places='" + places + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
