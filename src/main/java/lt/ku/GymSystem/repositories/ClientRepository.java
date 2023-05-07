package lt.ku.GymSystem.repositories;

import lt.ku.GymSystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    default List<Client> getClients() {
        return this.findAll();
    }

    Client findByUsername(String username);
}
