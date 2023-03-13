package lt.ku.GymSystem.repositories;

import lt.ku.GymSystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
