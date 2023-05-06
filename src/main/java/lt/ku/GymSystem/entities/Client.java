package lt.ku.GymSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Vardas yra privalomas laukelis.")
    @Length(min = 3, max = 20, message = "Vardas turi būti nuo 3 iki 20 simbolių.")
    @Column
    private String name;

    @NotEmpty(message = "Pavardė yra privalomas laukelis.")
    @Length(min = 3, max = 25, message = "Pavardė turi būti nuo 3 iki 25 simbolių.")
    @Column
    private String surname;

    @NotEmpty(message = "El. paštas yra privalomas laukelis.")
    @Email(message = "Neteisingas el. paštas.")
    @Column
    private String email;

    @NotEmpty(message = "Tel. Numeris yra privalomas laukelis.")
    @Length(max = 15, message = "Tel. Numeris turi būti ne ilgesnis nei 15 simbolių.")
    @Column
    private String phone;

    public Object getAttribute(String name) {

        return switch (name.toLowerCase()) {
            case "id" -> getId();
            case "name" -> getName();
            case "surname" -> getSurname();
            case "email" -> getEmail();
            case "phone" -> getPhone();
            default -> "";
        };
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    public Client() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}