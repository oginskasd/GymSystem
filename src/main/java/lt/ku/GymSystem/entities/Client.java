package lt.ku.GymSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name="clients")
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Prisijungimo vardas yra privalomas laukelis.")
    @Length(min = 3, max = 20, message = "Prisijungimo vardas turi būti nuo 3 iki 20 simbolių.")
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty(message = "Slaptažodis yra privalomas laukelis.")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private String role = "user";

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(this.role));

        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}