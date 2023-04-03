package lt.ku.GymSystem.repositories;

import lt.ku.GymSystem.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
}
