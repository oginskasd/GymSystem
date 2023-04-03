package lt.ku.GymSystem.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="registrations")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "workout")
    private List<Client> clients;


    public Registration() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> students) {
        this.clients = students;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                '}';
    }
}