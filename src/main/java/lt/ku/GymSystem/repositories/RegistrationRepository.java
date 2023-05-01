package lt.ku.GymSystem.repositories;

import lt.ku.GymSystem.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    default List<Registration> getRegistrations() {
        return this.findAll();
    }

}
