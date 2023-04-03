package lt.ku.GymSystem.repositories;

import lt.ku.GymSystem.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
}
